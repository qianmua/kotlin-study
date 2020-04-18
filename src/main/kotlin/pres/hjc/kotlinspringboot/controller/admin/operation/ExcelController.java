package pres.hjc.kotlinspringboot.controller.admin.operation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/18
 * @time 13:06
 */
@Controller
@RequestMapping("/down")
public class ExcelController {

    @RequestMapping("excel/{id}")
    @ResponseBody
    public String downExcel(@PathVariable("id")int id, HttpServletResponse response) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(14);
        String fileName = "1556083617985.png";
        String filePath = "F:\\ACG_pictures_Main\\bili\\";
        File file = new File(filePath);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte buffer[] = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download  successfully!");
                return "successfully";
            } catch (Exception e) {
                System.out.println("Download  failed!");
                return "failed";
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "null";
    }
}
