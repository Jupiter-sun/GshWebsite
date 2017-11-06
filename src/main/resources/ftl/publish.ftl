<span>${title!}</span>
<#assign  xy_id = ''/>
<#assign  flagIndex = 0/>
<table border="0" width="600">
    <#list list as item>
        <#if  item_index != 0 && flagIndex%6 == 0><#assign  flagIndex = 0/></tr><tr></#if>
        <#if item.xyId?? && item.xyId != xy_id>
            <#assign  flagIndex = 0/>
            <#assign  xy_id = item.xyId! />
            <#if item_index != 0 ></tr></#if>
            <tr><td height="30" colspan="6">${item.xy!}</td></tr>
            <tr>
        </#if>
        <td width="16%" height="25">${item.xm!}</td>
        <#assign  flagIndex = flagIndex + 1 />
        <#if !item_has_next></tr></#if>
    </#list>
</table>