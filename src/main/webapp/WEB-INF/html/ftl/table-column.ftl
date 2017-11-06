<#--
表格列标签：展示数据列。
	title：标题（列头）。直接显示字符串。默认""。
	width：列宽。默认""。
	align：对齐方式。
	class：css样式class
	style：css样式style
-->
<#macro column title="" width="80" align="center" style="" tips="false">
    <#if title=""><td>title all not assign!</td><#return></#if>
    <#if i==-1>
    <th width="${width}" align="${align}"><#if title!="">${title}</#if></th><#rt/>
    <#else>
    <td class="td" align="${align}" width="${width}"<#if tips="true"> title="<#nested/>"</#if><#if style!=""> style="${style}"</#if>><#nested/></td><#rt/>
    </#if>
</#macro>
<#macro column_checkbox name val value width="10" onclick="">
    <#if i==-1>
    <th align="center"<#if width!=""> width="${width}"</#if>><@p.checkbox onclick="checkbox('${name}',this.checked)"/></th><#rt/>
    <#else>
    <td class="td" align="center"<#if width!=""> width="${width}"</#if>><@p.checkbox name="${name}" value="${value}" val="${val}"/></td><#rt/>
    </#if>
</#macro>

<#macro column_dynamic columns item itemid="">
    <#list columns as col>
        <#if !col.url??>
            <@p.column width=col.displaywidth! title=col.fielddesc! tips="true">${item[col.fieldid!]!}</@p.column><#t/>
        <#else>
            <@p.column width=col.displaywidth! title=col.fielddesc!>
            <a href='javascript:void(0)'  style='text-decoration: underline;' onclick='javascript:${col.url!}("${itemid!}");return false;' >${item[col.fieldid!]!}</a>
            </@p.column><#t/>
        </#if>
    </#list>
</#macro>