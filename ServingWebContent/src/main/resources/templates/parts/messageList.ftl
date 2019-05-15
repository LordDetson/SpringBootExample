<#include "security.ftl">
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <div class="card-header">
                ${message.tag}
            </div>
            <#if message.filename??>
                <img class="card-img-top" src="/img/${message.filename}">
            </#if>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <p>${message.text}</p>
                    <footer class="blockquote-footer">
                        Message <a href="/user-messages/${message.author.id}">
                            <cite title="Source Title">${message.author.username}</cite>
                        </a>
                        <#if message.author.id == currentUserId>
                            <a class="btn btn-primary"
                               href="/user-messages/${message.author.id}?message=${message.id}">Edit</a>
                        </#if>
                    </footer>
                </blockquote>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</div>