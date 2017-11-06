<#--
表格标签：用于显示列表数据。
id: table外层的div，刷新列表用
	sytle：table的style样式。默认""。
-->
<#macro table value id url style="">
    <#if value?is_sequence><#local pageList=value/><#else><#local pageList=value.rows/></#if>
    <#if (pageList?size>0)>
    <div id="amtable_${id}" class="dataTables_wrapper" style="overflow:hidden;border-left: 1px solid #dddddd;border-right: 1px solid #dddddd;border-bottom: 1px solid #dddddd;">
        <div class="am-table-bordered">
            <div id="amhead_${id}" style="overflow:hidden;">
                <table class="am-table table-striped table-hover" style="border-left:none;">
                    <#list pageList as row>
                        <#if row_index==0>
                            <#assign i=-1/>
                            <thead><tr><#nested row,i,true/></tr></thead>
                            <#break/>
                        </#if>
                    </#list>
                </table>
            </div>
            <div id="ambody_${id}" style="overflow-y: auto;">
                <table class="am-table table-striped table-hover" style="border-left:none;" >
                    <tbody>
                        <#list pageList as row>
                            <#assign i=row_index has_next=row_has_next/>
                        <tr><#nested row,row_index,row_has_next/></tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <#if !(value?is_sequence)>
        <div id="page_${id}" class="row">
            <input type="hidden" name="currentPageNumber" value="${value.page}"/>

            <div style="float:left;padding-right:12px;">
                <div class="dataTables_paginate paging_simple_numbers">
                    <ul class="pagination" id="${id}_pagination">
                    </ul>
                </div>
            </div>
            <div style="float:right;padding-left:12px;padding-right:12px;">
                <div style="font-size:13px;">${value.viewStart}&nbsp;-&nbsp;${value.viewEnd}&nbsp;&nbsp;&nbsp;共&nbsp;${value.records}&nbsp;条</div>
            </div>
        </div>
        </#if>
    </div>
    <#if !(value?is_sequence)>
    <script type="text/javascript">
        createPage("${id}_pagination","${id}",${value.page}, ${value.pageSize}, ${value.records},"${ctx}${url}");
        $("#ambody_${id}").scroll(function() {
            var left= $(this).scrollLeft();
            $("#amhead_${id}").animate({marginLeft:-left},{ duration:0 , queue:false });
        });
    </script>
    </#if>
    </#if>
    <#if (pageList?size<=0)>
    <div class="dataTables_wrapper form-inline">
        <table class="am-table table-striped table-bordered table-hover">
            <#assign obj={id:""}/>
            <#assign i=-1/>
            <thead><tr><#nested obj,i,false/></tr></thead>
        </table>
        <div class="am-alert am-alert-secondary align-center" style="padding-top:100px;">
            <img src="${ctx}/static/images/no_data.png"/>
        </div>
    </div>

    </#if>
</#macro>