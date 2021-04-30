<%
OPEN QROSS;

VAR $api := SELECT title, description, method, path, return_value, creator, mender, return_value_example, pql, default_values, params, CAST(create_time AS CHAR) AS create_time, CAST(update_time AS CHAR) AS update_time  FROM qross_api_in_one WHERE id=$id -> FIRST ROW;
VAR $params := $api.params IF NULL '' SPLIT '&' AND '=';
VAR $values := $api.default_values IF NULL '' SPLIT '&' AND '=';
%>

# <%=$api.title IF NULL 'Untitled' %>

<b class="gray f16"><%=$api.method%></b> <b style="color: #DDDDDD; font-size: 1.25rem">: </b> <b class="f20" click-to-copy><%=$api.path%></b> 

-- 10 --

接口描述：<%=$api.description IF NULL 'N/A'%>

创建者：<%=$api.creator IF NULL 'N/A'%>

维护者：<%=$api.mender IF NULL 'N/A'%>

参数列表：

<table cellpadding="8" cellspacing="1" border="0">
    <tr>
        <th>参数名</th>
        <th>参数说明</th>
        <th>默认值</th>
    </tr>    
    <% 
    IF $params IS NOT EMPTY THEN
        FOR $name, $desc IN $params LOOP %>
        <tr>
            <td click-to-copy><%=$name%></td>
            <td><%=$desc REPLACE '%20' TO ' ' REPLACE '%3D' TO '=' REPLACE '%26' TO '&' %></td>
            <td><%=$values GET $name IF NULL '&nbsp;'%></td>
        </tr>
    <% END LOOP;
    ELSIF $values IS NOT EMPTY THEN
        FOR $name, $value IN $values LOOP %>
        <tr>
            <td click-to-copy><%=$name%></td>
            <td><%=$params GET $name IF NULL '&nbsp;' REPLACE '%20' TO ' ' REPLACE '%3D' TO '=' REPLACE '%26' TO '&' %></td>
            <td><%=$value%></td>            
        </tr>
     <% END LOOP;
    END IF %> 
</table>

-- 10 --

返回值类型：<%=$api.return_value IF NULL ''%>

返回值示例：

<textarea coder="json" clear-after="click:#ResetLink"><%=$api.return_value_example IF NULL ''%></textarea>

/gray:如果返回值示例不正确，可以<a id="ResetLink" style="font-weight: bold;" onclick+="INVOKE io.qross.app.OneApi.resetReturnValueExample(#{id});" confirm-text="确定要清空返回值示例数据吗？" success-text="已重置。">手动清空</a>，当接口下次被访问时会自动保存。/  

接口逻辑：

```sql
<%=$api.pql IF NULL ''%>
```

-- 20 --

/gray:如果接口路径或请求方法已经被修改或者因为其他原因不再使用，则可以/ <a href="/doc/oneapi/general" onclick+="OPEN QROSS; DELETE FROM qross_api_in_one WHERE id=#{id}" style="font-weight: bold" confirm-text="确定要删除这个接口吗？">删除这个接口</a>。

-- 50 --