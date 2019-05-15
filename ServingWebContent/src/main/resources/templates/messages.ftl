<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" action="/messages" method="get">
                <label class="col-sm-2 col-form-label">Tag:</label>
                <input class="form-control" type="text" name="valueFilter" value="${valueFilter?ifExists}"
                       placeholder="Search by tag"/>
                <button class="btn btn-primary ml-2" type="submit">Search</button>
            </form>
        </div>
    </div>
    <#include "parts/messageEditor.ftl"/>
    <#include "parts/messageList.ftl"/>
</@c.page>