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
    <a class="btn btn-primary" data-toggle="collapse" href="#addNewMessage" role="button" aria-expanded="false"
       aria-controls="addNewMessage">
        Add new message
    </a>
    <div class="collapse <#if message??>show</#if>" id="addNewMessage">
        <div class="form-group mt-3">
            <form action="/addNewMessage" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <input class="form-control ${(textError??)?string('is-invalid', '')}" type="text" name="text"
                           placeholder="Text" value="<#if message??>${message.text}</#if>"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control ${(tagError??)?string('is-invalid', '')}" type="text" name="tag"
                           placeholder="Tag" value="<#if message??>${message.tag}</#if>"/>
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input class="form-control" type="file" name="file" id="customFile"/>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Add message</button>
                </div>
            </form>
        </div>
    </div>
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
                        <footer class="blockquote-footer">Message <cite
                                    title="Source Title">${message.author.username}</cite></footer>
                    </blockquote>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>