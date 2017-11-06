<#--
表格列标签：展示数据列。
-->
<#macro checkbox name="" value="" val="" onclick="">
<label class="position-relative">
    <input type="checkbox" class="ace" name="${name}" value="${value}" onclick="${onclick}" <#if val!="" && value!="" && value==val>checked</#if>/>
    <span class="lbl"></span>
</label>
</#macro>

<#macro checkValue value values>
    <#if values!="" && value!="">
        <#assign arrs=(values?split(","))/>
        <#list arrs as item>
            <#if item==value>checked</#if>
        </#list>
    </#if>
</#macro>

<#macro radio name items value="">
    <#if items!="">
        <#assign arrs=(items?split("|"))/>
        <#list arrs as item>
            <#assign arr=(item?split("#"))/>
            <#if arr?size==1>
            <div class="radio inline"><#rt/>
                <label><#rt/>
                    <input type="radio" class="ace" value="${arr[0]}" name="${name}" <@p.checkValue value="${arr[0]}" values="${value}"/>/><#rt/>
                    <span class="lbl"> ${arr[0]}</span><#rt/>
                </label><#rt/>
            </div><#rt/>
            <#else>
            <div class="radio inline"><#rt/>
                <label><#rt/>
                    <input type="radio" class="ace" value="${arr[0]}" name="${name}" <@p.checkValue value="${arr[0]}" values="${value}"/>/><#rt/>
                    <span class="lbl"> ${arr[1]}</span><#rt/>
                </label><#rt/>
            </div><#rt/>
            </#if>
        </#list>
    </#if>
</#macro>

<#macro select id items name="" value="" class="col-xs-10 col-sm-10" placeholder="" multiple="" validate="">
    <#if name==""><#local name=id/></#if>
    <#if items!="">
        <#assign arrs=(items?split("|"))/>
        <select id="${id}" name="${name}" class="chosen-select ${class}" data-placeholder="${placeholder}" <#if multiple!=""&&multiple=="true">multiple</#if> <#if validate!=""> ${validate}</#if>><#rt/>
        <option value=""></option><#rt/>
        <#list arrs as item>
            <#assign arr=(item?split("#"))/>
            <#if arr?size==1>
            <option value="${arr[0]}" <#if value!="" && value==arr[0]>selected</#if>>${arr[0]}</option><#rt/>
            <#else>
            <option value="${arr[0]}" <#if value!="" && value==arr[0]>selected</#if>>${arr[1]}</option><#rt/>
            </#if>
        </#list>
        </select><#rt/>
        <script type="text/javascript">
            $("#${id}").chosen({allow_single_deselect:true});
        </script>
    </#if>
</#macro>

<#macro selectValue value values>
    <#if values!="" && value!="">
        <#assign arrs=(values?split(","))/>
        <#list arrs as item>
            <#if item==value>selected</#if>
        </#list>
    </#if>
</#macro>

<#macro selectCode id name="" parentId="" multiple="" value="" validate="" placeholder="" class="col-xs-10 col-sm-10" isdm="true">
    <#if name==""><#local name=id/></#if>
    <#if placeholder==""><#local placeholder="请选择"/></#if>
<select id="${id}" name="${name}" class="chosen-select ${class}" data-placeholder="${placeholder}" <#if multiple!=""&&multiple=="true">multiple</#if>><#rt/>
    <option value=""></option><#rt/>
    <@common_list type="code" parentId="${parentId}">
        <#list tag_list as item>
            <#if isdm=="true">
                <option value="${item.id}" <@p.selectValue value="${item.id}" values="${value}"/>>${item.value}</option><#rt/>
            <#else>
                <option value="${item.value}" <@p.selectValue value="${item.value}" values="${value}"/>>${item.value}</option><#rt/>
            </#if>
        </#list>
    </@common_list>
</select><#rt/>
<script type="text/javascript">
    $("#${id}").chosen({allow_single_deselect:true});
</script>
</#macro>

<#macro expression id name="" quotaId="" value="" class="" style="" placeholder="">
<span class="input-icon input-icon-right ${class}" style="${style}">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" onclick="select_${id}()" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
</span>
<script type="text/javascript">
    function call_${id}(params){
        if(params!=null&&params!="") {
            $("#${id}").val(params);
        }
        else{
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        var expression = encodeURIComponent("${value}");
        $.dialog("${ctx}/select/cepingExpression?quotaId=${quotaId}&value="+expression+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
</#macro>