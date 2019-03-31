package top.simpleito.crossorigindemo;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class TestCros {

    /**
     * 基于JSONP进行跨域请求
     */
    @GetMapping("/simple")
    public String test(HttpServletRequest request) throws IOException {
        String jsText = "";
        jsText += request.getParameter("callback"); //获取回调方法名称
        String data = "{\"id\":1,\"name\":\"Jack\"}"; //服务器待返回Json数据
        jsText = jsText + "(" + data + ");"; //拼接写明为回调方法调用，并将数据以参数形式传入
        return jsText;
    }


    /**
     * 基于CORS的简单跨域请求
     */
    @RequestMapping("/corsapi")
    public String simpleRequest(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin","*");
        String data = "{\"id\":1,\"name\":\"Jack\"}";
        return data;
    }

    /**
     * 基于CORS的非简单跨域请求
     */
    @PostMapping("/cors-no-simple")
    public String noSimple(@RequestBody Map parm, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin","http://localhost:3000");
        return "服务器收到了数据"+parm.toString();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/test-cross-annotation")
    public String crosAnnotationTest(@RequestBody Map parm){
        return "服务器收到了数据"+parm.toString();
    }
}
