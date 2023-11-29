package com.example.imaubookmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.dao.SysBookDao;
import com.example.imaubookmanager.pojo.SysBookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class SysBookImpl {

    @Autowired
    private SysBookDao sysBookDao;
    private String UPLOAD_DIR;
    private static final String FILE_DIR = "E:\\javaStudy\\imau-bookmanager-file";
    public String uploadfile(MultipartFile file){
        // 获取当前项目文件夹路径
        String projectDir = System.getProperty("user.dir");
        // 设置上传目录路径为当前项目文件夹同级目录下的 imau-bookmanager-file 文件夹
        UPLOAD_DIR = Paths.get(projectDir).getParent().resolve("imau-bookmanager-file").toString();

        if (file.isEmpty()) {
            throw new RuntimeException("上传文件为空");
        }

        try {
            // 创建上传目录（如果不存在）
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 获取文件的字节流并保存到指定路径
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename().replaceAll("\\\\", "/");
            Path path = Paths.get(UPLOAD_DIR + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败");
        }
    }

    public void downloadFile(String fileName, HttpServletResponse response) {
        File file = new File(FILE_DIR + File.separator + fileName);

        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            try (FileInputStream fis = new FileInputStream(file);
                 OutputStream os = response.getOutputStream()) {
                FileCopyUtils.copy(fis, os);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 文件不存在时的处理
            System.out.println("文件不存在");
        }
    }


    public int addBook(SysBookPojo book) {


            int insertCount = sysBookDao.insert(book);
            return insertCount;

    }

    public int updateBook(SysBookPojo book) {
        SysBookPojo sysBook = sysBookDao.selectById(book.getId());
        if (sysBook == null) {
            throw new RuntimeException("书籍不存在");
        }
            int updateCount = sysBookDao.updateById(book);
            return updateCount;


    }

    public int deleteBook(Integer id) {
        SysBookPojo book = sysBookDao.selectById(id);
            if (book == null) {
                throw new RuntimeException("书籍不存在");
            }
            int deleteCount = sysBookDao.deleteById(id);
            return  deleteCount;

    }

    public SysBookPojo getBookById(Integer id) {

            SysBookPojo book = sysBookDao.selectById(id);
            //判断是否存在

            if (book == null) {
                throw new RuntimeException("书籍不存在");
            }
            return book;

    }

    public Page<SysBookPojo> getBooksByPage(int pageNum, int pageSize, String keyword,int count,String type) {

            Page<SysBookPojo> page = new Page<>(pageNum, pageSize);
            QueryWrapper<SysBookPojo> queryWrapper = new QueryWrapper<>();
        // 判断 keyword 是否为空来决定是否添加 like 查询条件
            if (StringUtils.isNotBlank(keyword)) {
                queryWrapper.and(wrapper -> wrapper
                        .like("name", keyword)
                        .or().like("author", keyword)
                        .or().like("detail", keyword)
                        .or().like("publisher", keyword)
                );
            }
            // 根据参数 count 是否为空，动态添加条件
            if (count >0) {
                queryWrapper.ge("count", count);
            }else if(count == 0){
                queryWrapper.eq("count", 0);
            }

            // 根据参数 type 是否为空，动态添加条件
            if (type != null && !type.isEmpty()) {
                queryWrapper.eq("type", type);
            }
            // 调用 MyBatis-Plus 提供的分页查询方法
            Page<SysBookPojo> sysBookPojo = sysBookDao.selectPage(page, queryWrapper);
            return sysBookPojo;


    }
}
