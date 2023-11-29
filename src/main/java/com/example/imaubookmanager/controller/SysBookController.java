package com.example.imaubookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysBookPojo;
import com.example.imaubookmanager.pojo.vo.SearchBookVO;
import com.example.imaubookmanager.service.SysBookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class SysBookController {
    @Autowired
    private SysBookImpl sysBookService;

    @PostMapping("/addBook")
    public ResponseResult addBook(@RequestBody SysBookPojo book) {
        try {
            int insertCount = sysBookService.addBook(book);
            if (insertCount == 1) {
                return new ResponseResult(HttpStatus.OK.value(), "添加成功");
            } else {
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
        }
    }

    @PostMapping("/updateBook")
    public ResponseResult updateBook(@RequestBody SysBookPojo book) {
        try {
            int updateCount = sysBookService.updateBook(book);
            if (updateCount == 1) {
                return new ResponseResult(HttpStatus.OK.value(), "更新成功");
            } else {
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
        }
    }
    @PostMapping("/deleteBook")
    public ResponseResult deleteBook(@RequestParam("id") Integer id) {
        try {
            int deleteCount = sysBookService.deleteBook(id);
            if (deleteCount == 1) {
                return new ResponseResult(HttpStatus.OK.value(), "删除成功");
            } else {
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除失败");
        }
    }
    @PostMapping("/getBookById")
    public ResponseResult getBookById(@RequestParam("id") Integer id) {
        try {
            System.out.println(id);
            SysBookPojo data = sysBookService.getBookById(id);
            if (data != null) {
                return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);
            } else {
                return new ResponseResult(HttpStatus.NOT_FOUND.value(), "书籍不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }
    }

    @PostMapping("/uploadfile")
    public ResponseResult uploadfile(@RequestParam("file") MultipartFile file) {
        try {

            String data = sysBookService.uploadfile(file);
            if (data.length()!=0) {
                return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);
            } else {
                return new ResponseResult(HttpStatus.NOT_FOUND.value(), "上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传失败");
        }
    }

    @GetMapping("/download/{fileName}")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        sysBookService.downloadFile(fileName, response);
    }

    @PostMapping("/getBooksByPage")
    public ResponseResult getBooksByPage(@RequestBody SearchBookVO searchBookVO) {
        try {
            Page<SysBookPojo> data = sysBookService.getBooksByPage(searchBookVO.getPageNum(), searchBookVO.getPageSize(), searchBookVO.getKeyWord(),searchBookVO.getCount(),searchBookVO.getType());
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }
    }
}
