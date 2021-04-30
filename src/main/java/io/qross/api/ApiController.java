package io.qross.api;

import io.qross.app.OneApi;
import io.qross.net.Http;
import io.qross.net.Json;
import io.qross.pql.PQL;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    //根目录
    @RequestMapping("/api/{name}")
    public Object OneApi(@PathVariable("name") String name) {
        return OneApi.request("/api/default/" + name);
    }

    //一层目录
    @RequestMapping("/api/{group}/{name}")
    public Object OneApi(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.request("/api/" + group + "/" + name);
    }

    //两层目录
    @RequestMapping("/api/{module}/{group}/{name}")
    public Object OneApi(@PathVariable("module") String module, @PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.request("/api/" + module + "/" + group + "/" + name);
    }

    //用户登录信息, 可在PQL中可使用全局变量 @userid, @username, @role
    @RequestMapping("/user/{group}/{name}")
    public Object OneApiAuth(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.signIn(1, "ted", "monitor").request("/api/" + group + "/" + "name");
    }

    @RequestMapping(value = "/api/cogo/cross", method = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT })
    public Object crossDomain(@RequestParam(value = "method") String method, @RequestParam(value = "url") String url, @RequestParam(value = "data") String data) {
        return Json.fromText(new Http(method, url, data).request()).findNode("/");
    }

    @RequestMapping(value = "/api/cogo/pql", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
    public Object runPQL(@RequestParam(value = "statement") String statement) {
        String pql = statement.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&");

        return PQL.open(pql).run();
    }

    // 获取动态Token
    @RequestMapping("/oneapi/secret")
    public Object OneApiSecret(@RequestParam("token") String token) {
        return OneApi.getSecretKey(token);
    }

    //刷新所有接口, 如果是在数据库中配置接口, 可通过这个接口随时刷新则无需重启项目
    @RequestMapping("/oneapi/refresh")
    public Object OneApiRefresh(@RequestParam("key") String key) {
        if (OneApi.authenticateManagementKey(key)) {
            return OneApi.refresh();
        }
        else {
            return "Access denied!";
        }
    }

    @RequestMapping("/oneapi/all")
    public Object OneApiAll(@RequestParam("key") String key) {
        if (OneApi.authenticateManagementKey(key)) {
            return OneApi.getAll();
        }
        else {
            return "Access denied!";
        }
    }

    @RequestMapping("/oneapi/settings")
    public Object OneApiSettings(@RequestParam("key") String key) {
        if (OneApi.authenticateManagementKey(key)) {
            return OneApi.getSettings();
        }
        else {
            return "Access denied!";
        }
    }

    @RequestMapping("/oneapi/logic")
    public Object OneApiLogic(@RequestParam("path") String path, @RequestParam("method") String method, @RequestParam("key") String key) {
        if (OneApi.authenticateManagementKey(key)) {
            return OneApi.pick(path, method);
        }
        else {
            return "Access denied!";
        }
    }

    //生成 token
    @RequestMapping("/oneapi/token")
    public Object OneApiToken() {
        return OneApi.getToken(32);
    }
}
