<#-- 学院 -->
<#macro xy id hiddenId hiddenName="" multiple="" value="" hiddenValue="" style="" zy="" validate="" zyId="" bj="" bjId="" placeholder="请选择学院">
<#if hiddenName==""><#local hiddenName=hiddenId/></#if>
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" <#if validate!=""> ${validate}</#if> onclick="select_${id}()" style="${style}" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
    <input type="hidden" id="${hiddenId}" name="${hiddenName}" value="${hiddenValue}"/>
</span>
<script type="text/javascript">
    function call_${id}(params) {
        <#if zy!="">
            $("#${zy}").val("");
        </#if>
        <#if zyId!="">
            $("#${zyId}").val("");
        </#if>
        <#if bj!="">
            $("#${bj}").val("");
        </#if>
        <#if bjId!="">
            $("#${bjId}").val("");
        </#if>
        if (params != null && params != "") {
            var selectValues = params.split(",");
            var value = "";
            var hiddenValue = "";
            for (var i = 0; i < selectValues.length; i++) {
                var item = selectValues[i].split("|||");
                if (i > 0) {
                    value += ",";
                    hiddenValue += ",";
                }
                hiddenValue += item[0];
                value += item[1];
            }
            <#if hiddenId!="">$("#${hiddenId}").val(hiddenValue);</#if>
            $("#${id}").val(value);
        } else {
            <#if hiddenId!="">$("#${hiddenId}").val("");</#if>
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        var hiddenValue = $("#${hiddenId}").val();
        $.dialog("${ctx}/select/xy?pinyin=true&multiple=${multiple}&value="+hiddenValue+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
</#macro>
<#-- 专业 -->
<#macro zy id hiddenId hiddenName="" multiple="" xyId="" nj="" value="" hiddenValue="" style="" bj="" bjId="" placeholder="请选择专业">
<#if hiddenName==""><#local hiddenName=hiddenId/></#if>
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" onclick="select_${id}()" style="${style}" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
    <input type="hidden" id="${hiddenId}" name="${hiddenName}" value="${hiddenValue}"/>
</span>
<script type="text/javascript">
    function call_${id}(params){
        <#if bj!="">
            $("#${bj}").val("");
        </#if>
        <#if bjId!="">
            $("#${bjId}").val("");
        </#if>
        if(params!=null&&params!="") {
            var selectValues = params.split(",");
            var value = "";
            var hiddenValue = "";
            for (var i = 0; i < selectValues.length; i++) {
                var item = selectValues[i].split("|||");
                if (i > 0) {
                    value += ",";
                    hiddenValue += ",";
                }
                hiddenValue += item[0];
                value += item[1];
            }
            $("#${hiddenId}").val(hiddenValue);
            $("#${id}").val(value);
        }
        else{
            $("#${hiddenId}").val("");
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        var xyId = "";
        <#if xyId!="">
            xyId = $("#${xyId}").val();
        </#if>
        var nj = "";
        <#if nj!="">
            nj = $("#${nj}").val();
        </#if>
        var hiddenValue = $("#${hiddenId}").val();
        $.dialog("${ctx}/select/zy?pinyin=true&multiple=${multiple}&value="+hiddenValue+"&xyId="+xyId+"&nj="+nj+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
</#macro>

<#-- 班级 -->
<#macro bj id hiddenId hiddenName="" multiple="" xyId="" nj="" zyId="" value="" hiddenValue="" style="" placeholder="请选择班级">
<#if hiddenName==""><#local hiddenName=hiddenId/></#if>
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" onclick="select_${id}()" style="${style}" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
    <input type="hidden" id="${hiddenId}" name="${hiddenName}" value="${hiddenValue}"/>
</span>
<script type="text/javascript">
    function call_${id}(params){
        if(params!=null&&params!="") {
            var selectValues = params.split(",");
            var value = "";
            var hiddenValue = "";
            for (var i = 0; i < selectValues.length; i++) {
                var item = selectValues[i].split("|||");
                if (i > 0) {
                    value += ",";
                    hiddenValue += ",";
                }
                hiddenValue += item[0];
                value += item[1];
            }
            $("#${hiddenId}").val(hiddenValue);
            $("#${id}").val(value);
        }
        else{
            $("#${hiddenId}").val("");
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        var zyId = "";
        <#if zyId!="">
            zyId = $("#${zyId}").val();
        </#if>
        var xyId = "";
        <#if xyId!="">
            xyId = $("#${xyId}").val();
        </#if>
        var nj = "";
        <#if nj!="">
            nj = $("#${nj}").val();
        </#if>
        var hiddenValue = $("#${hiddenId}").val();
        $.dialog("${ctx}/select/bj?pinyin=true&multiple=${multiple}&value="+hiddenValue+"&xyId="+xyId+"&nj="+nj+"&zyId="+zyId+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
</#macro>

<#macro common id type pinyin="false" name="" hiddenId="" hiddenName="" parentId="" multiple="" value="" hiddenValue="" class="" style="" validate="" self="false"
onchange="" onblur="" readonly="false" placeholder="请选择">
<#if name==""><#local name=id/></#if>
<#if self=="false">
<span class="input-icon input-icon-right ${class}" style="${style}"><#rt/>
    <input type="text" id="${id}" name="${name}" placeholder="${placeholder}" onclick="select_${id}()" value="${value}" <#rt/>
        <#if validate!=""> ${validate}</#if><#rt/>
        <#if onblur!=""> onblur="${onblur}"</#if><#rt/>
        <#if readonly=="true"> readonly="readonly"</#if><#rt/>
    /><#rt/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
    <#if hiddenId!="">
    <#if hiddenName==""><#local hiddenName=hiddenId/></#if>
    <input type="hidden" id="${hiddenId}" name="${hiddenName}" value="${hiddenValue}"/></#if><#rt/>
</span>
<script type="text/javascript">
    function call_${id}(params){
        if(params!=null&&params!="") {
            var selectValues = params.split(",");
            var value = "";
            var hiddenValue = "";
            for (var i = 0; i < selectValues.length; i++) {
                var item = selectValues[i].split("|||");
                if (i > 0) {
                    value += ",";
                    hiddenValue += ",";
                }
                hiddenValue += item[0];
                value += item[1];
            }
                <#if hiddenId!="">$("#${hiddenId}").val(hiddenValue);</#if>
            $("#${id}").val(value);
        }
        else{
                <#if hiddenId!="">$("#${hiddenId}").val("");</#if>
            $("#${id}").val("");
        }
        <#if onchange!="">${onchange};</#if><#rt/>
    }
    function select_${id}(){
        var hidden = "";
            <#if hiddenId!="">hidden="true";var hiddenValue = $("#${hiddenId}").val();</#if>
            <#if hiddenId=="">var hiddenValue = encodeURI(encodeURI($("#${id}").val()));</#if>
        $.dialog("${ctx}/select/common?pinyin=${pinyin}&multiple=${multiple}&parentId=${parentId}&hidden="+hidden+"&type=${type}&value="+hiddenValue+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
<#elseif self=="true">
    <@common_list type="${type}" parentId="${parentId}">
        <#if multiple=="true">
            <#list tag_list as item>
            <label class="position-relative">
                <#if hiddenId!="">
                    <input class="ace" type="checkbox" value="${item.id}" name="${hiddenId}" <@p.checkValue value="${item.id}" values="${hiddenValue}"/>/><span class="lbl">${item.value}</span><#rt/>
                <#else>
                    <input class="ace" type="checkbox" value="${item.value}" id="${id}" name="${name}" <@p.checkValue value="${item.value}" values="${value}"/>/><span class="lbl">${item.value}</span><#rt/>
                </#if>
            </label>
            </#list>
        <#else>
            <#list tag_list as item>
                <#if hiddenId!="">
                <div class="radio inline"><#rt/>
                    <label><#rt/>
                        <input class="ace" type="radio" value="${item.id}" name="${hiddenId}" <@p.checkValue value="${item.id}" values="${hiddenValue}"/>/><#rt/>
                        <span class="lbl"> ${item.value}</span><#rt/>
                    </label><#rt/>
                </div><#rt/>
                <#else>
                <div class="radio inline"><#rt/>
                    <label><#rt/>
                        <input class="ace" type="radio"  value="${item.value}" id="${id}" name="${name}" <@p.checkValue value="${item.value}" values="${value}"/>/><#rt/>
                        <span class="lbl"> ${item.value}</span><#rt/>
                    </label><#rt/>
                </div><#rt/>
                </#if>
            </#list>
        </#if>
    </@common_list>
</#if>
</#macro>


<#macro nj id style="" value="" placeholder="请选择年级" multiple="" zy="" zyId="" bj="" bjId="" validate="">
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" <#if validate!=""> ${validate}</#if> onclick="select_${id}()" style="${style}" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
</span>
<script type="text/javascript">
    function call_${id}(params){
        <#if zy!="">
            $("#${zy}").val("");
        </#if>
        <#if zyId!="">
            $("#${zyId}").val("");
        </#if>
        <#if bj!="">
            $("#${bj}").val("");
        </#if>
        <#if bjId!="">
            $("#${bjId}").val("");
        </#if>
        if(params!=null&&params!=""){
            var selectValues = params.split(",");
            var value = "";
            for(var i=0; i<selectValues.length; i++){
                var item = selectValues[i].split("|||");
                if(i>0){
                    value+=",";
                }
                value+=item[1];
            }
            $("#${id}").val(value);
        }
        else{
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        var value = $("#${id}").val();
        $.dialog("${ctx}/select/nj?multiple=${multiple}&value="+value+"&callback=call_${id}","${placeholder}","700px","300px");
    }
</script>
</#macro>


<#macro demo id>
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="" onclick="select_${id}()"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
</span>
<script type="text/javascript">
    function call_${id}(params){

    }
    function select_${id}(){
        var value = $("#${id}").val();
        $.dialog("${ctx}/select/demo?multiple=false&value="+value+"&callback=call_${id}","请选择","700px","300px");
    }
</script>
</#macro>

<#-- 省市县 -->
<#macro area id name="" value="" placeholder="请选择" validate="">
<#if name=""><#local name=id/></#if>
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" <#if validate!=""> ${validate}</#if> onclick="select_${id}()" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
</span>
<script type="text/javascript">
    function call_${id}(params){
        if(params!=null&&params!="") {
            var province = params.province;
            var city = params.city;
            var locale = '';
            if ( params.locale ){
                locale = params.locale;
            }
            $("#${id}").val(province+city+locale);
        }
        else{
            $("#${id}").val("");
        }
    }
    function select_${id}(){
        $.dialog("${ctx}/select/area?callback=call_${id}","${placeholder}","700px","400px");
    }
</script>
</#macro>


<#macro xn id hiddenId="" style="" value="" hiddenValue="" onchange="" placeholder="请选择学年" validate="">
<span class="input-icon input-icon-right">
    <input type="text" id="${id}" name="${id}" class="am-form-field" placeholder="${placeholder}" <#if validate!=""> ${validate}</#if> onclick="select_${id}()" style="${style}" value="${value}"/>
    <i style="cursor:pointer;" class="ace-icon fa fa-search blue" onclick="select_${id}()"></i>
    <#if hiddenId!="">
        <input type="hidden" id="${hiddenId}" name="${hiddenId}" value="${hiddenValue}"/>
    </#if>
</span>
<script type="text/javascript">
    function call_${id}(params){
        if(params!=null&&params!=""){
            var selectValues = params.split(",");
            var value = "";
            var hiddenValue = "";
            for (var i = 0; i < selectValues.length; i++) {
                var item = selectValues[i].split("|||");
                if (i > 0) {
                    value += ",";
                    hiddenValue += ",";
                }
                hiddenValue += item[0];
                value += item[1];
            }
                <#if hiddenId!="">$("#${hiddenId}").val(hiddenValue);</#if>
            $("#${id}").val(value);
        }else {
                <#if hiddenId!="">$("#${hiddenId}").val("");</#if>
            $("#${id}").val("");
        }
            <#if onchange!="">${onchange};</#if><#rt/>
    }
    function select_${id}(){
        var hidden = "";
            <#if hiddenId!="">hidden="true";var hiddenValue = $("#${hiddenId}").val();</#if>
            <#if hiddenId=="">var hiddenValue = encodeURI(encodeURI($("#${id}").val()));</#if>
        $.dialog("${ctx}/select/xn?multiple=false&hidden="+hidden+"&value="+hiddenValue+"&callback=call_${id}","${placeholder}","700","400");
    }
</script>
</#macro>