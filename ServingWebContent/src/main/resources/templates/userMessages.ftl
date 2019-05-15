<#import "parts/common.ftl" as c>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/messageEditor.ftl"/>
    </#if>
    <#include "parts/messageList.ftl"/>
</@c.page>