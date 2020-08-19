package com.xiang.springboot01.modules.test.controller;


import com.xiang.springboot01.modules.test.entity.City;
import com.xiang.springboot01.modules.test.entity.Country;
import com.xiang.springboot01.modules.test.service.CityService;
import com.xiang.springboot01.modules.test.service.CountryService;
import com.xiang.springboot01.modules.test.vo.ApplicationTest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2020年08月10日 13:55:00
 */


@Controller
@RequestMapping("/test")
public class TestController {
  /*  @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private String age;
    @Value("${com.sex}")
    private String sex;
    @Value("${com.random}")
    private String random;
*/
    @Autowired
    private ApplicationTest applicationTest;

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;


    private final static Logger LOGGER= LoggerFactory.getLogger(TestController.class);


    @GetMapping("/three")
    public String indexSimpleTestPage(){
        return "indexSimple";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
        try {
            Resource resource =new UrlResource(Paths.get("F:\\uploadFile\\"+fileName).toUri());
            String filename = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");//下载文件名中文乱码
            if (resource.exists() && resource.isReadable()){
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            String.format("attachment; filename=\"%s\"",filename))
                    .body(resource);
            }
         }catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
     */
    @RequestMapping("/download1")
    public void downloadFile1(HttpServletRequest request,
                              HttpServletResponse response, @RequestParam String fileName) {
        String filePath = "F:\\uploadFile\\" + File.separator + fileName;
        File downloadFile = new File(filePath);

        if (downloadFile.exists()) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int)downloadFile.length());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    String.format("attachment; filename=\"%s\"", fileName));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(downloadFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                } catch (Exception e2) {
                    LOGGER.debug(e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * 以包装类 IOUtils 输出文件
     */
    @RequestMapping("/download2")
    public void downloadFile2(HttpServletRequest request,
                              HttpServletResponse response, @RequestParam String fileName) {
        String filePath = "F:\\uploadFile\\" + File.separator + fileName;
        File downloadFile = new File(filePath);

        try {
            if (downloadFile.exists()) {
                response.setContentType("application/octet-stream");
                response.setContentLength((int)downloadFile.length());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", fileName));

                InputStream is = new FileInputStream(downloadFile);
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile [] files,RedirectAttributes redirectAttributes){
        boolean empty =true;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String destFilePath="F:\\uploadFile\\"+file.getOriginalFilename();
            File destFile = new File(destFilePath);
            try {
                file.transferTo(destFile);
                empty=false;

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message","上传失败");
            }

        }
        if (empty){
            redirectAttributes.addFlashAttribute("message","请选择文件");
        }else {
            redirectAttributes.addFlashAttribute("message","上传成功");
        }

        return "redirect:/test/one";
    }


    /*
    * 127.0.0.1/test/file   ---post
    * */
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        String destFilePath="F:\\uploadFile\\"+file.getOriginalFilename();
        File destFile = new File(destFilePath);
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择文件");
            return "redirect:/test/one";
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","上传失败");
        }
        redirectAttributes.addFlashAttribute("message","上传成功");

        return "redirect:/test/one";
    }


    /**
     * 127.0.0.1/test/one ---get
     */
    @GetMapping("/one")
    public String One(ModelMap modelMap){
        int countryId = 522;
        List<City> cities = cityService.selectCityByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.selectCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/city/city");
        /*modelMap.addAttribute("template","test/one");*/
        //返回外层碎片组装器

        return "index";
    }

    /**
     * 127.0.0.1/test/two ---get
     */
    @GetMapping("/two")
    public String Two(ModelMap modelMap){
        modelMap.addAttribute("template","test/two");
        //返回外层碎片组装器

        return "index";
    }


    /**
     * @return http://localhost:8081/test/logTest ---get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest(){
        LOGGER.trace("This is trace log");
        LOGGER.trace("This is debug log");
        LOGGER.trace("This is info log");
        LOGGER.trace("This is warn log");
        LOGGER.trace("This is error log");
        return "这是一个日志测试";
    }

/*    *//**
     * @return http://localhost:8081/test/conTest ---get
     *//*
    @GetMapping("/conTest")
    @ResponseBody
    public String configTest(){
        StringBuffer sb=new StringBuffer();
        sb.append(name).append("====").
                append(age).append("====").
                append(sex).append("====").
                append(random).append("<br>");
        sb.append(applicationTest.getPort()).append("====").
                append(applicationTest.getName()).append("====").
                append(applicationTest.getAge()).append("====").
                append(applicationTest.getSex()).append("====").
                append(applicationTest.getRandom());
        return sb.toString();
    }*/

/*
    */
/**
     * @return http://localhost:8081/test/proTest ---get
     *//*

    @GetMapping("/proTest")
    @ResponseBody
    public String propertyTest(){
        StringBuffer sb=new StringBuffer();
        return sb.append(name).append("====").
                append(age).append("====").
                append(sex).append("====").
                append(random).toString();
    }
*/

    /**
     * @return 127.0.0.1/test/testOne?paramKey=fuuuuk ---get
     */
    @GetMapping("/testOne")
    @ResponseBody
    public String testOne(@RequestParam("paramKey") String paramValue, HttpServletRequest request){
        String paramValue2=request.getParameter("paramKey");
        return "这是一个测试11111------"+paramValue+"-----"+paramValue2;
    }
}
