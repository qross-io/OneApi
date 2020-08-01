package io.qross.api;

import io.qross.app.OneApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    //默认接口
    @RequestMapping("/{name}")
    public Object OneApi(@PathVariable("name") String name) {
        return OneApi.request("default/" + name);
    }

    //根目录
    @RequestMapping("/{group}/{name}")
    public Object OneApi(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.request(group + "/" + "name");
    }

    //一层目录
    @RequestMapping("/{module}/{group}/{name}")
    public Object OneApi(@PathVariable("module") String module, @PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.withJsonParameters().request(module + "/" + group + "/" + name);
    }

    //两层目录
    @RequestMapping("/{module1}/{module2}/{group}/{name}")
    public Object OneApi(@PathVariable("module1") String module1, @PathVariable("module2") String module2, @PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.request(module1 + "/" + module2 + "/" + group + "/" + name);
    }

    //其他路径格式
    @RequestMapping("/api/{group}/{name}")
    public Object OneApiCustomPath(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.request(group + "/" + "name");
    }

    //传递Json参数
    @RequestMapping("/json/{group}/{name}")
    public Object OneApiWithJson(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.withJsonParameters().request(group + "/" + "name");
    }

    //用户登录信息, 可在PQL内使用 @userid, @username, @role
    @RequestMapping("/user/{group}/{name}")
    public Object OneApiAuth(@PathVariable("group") String group, @PathVariable("name") String name) {
        return OneApi.signIn(1, "username", "role").request(group + "/" + "name");
    }

    //刷新所有接口, 如果是在数据库中配置接口, 可通过这个接口随时刷新则无需停机
    @RequestMapping("/oneapi/refresh")
    public Object OneApiRefresh() {
        return OneApi.refresh();
    }

    //生成token
    @RequestMapping("/oneapi/token")
    public Object OneApiToken() {
        return OneApi.getToken(32);
    }
}
