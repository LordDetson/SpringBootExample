<a class="btn btn-primary" data-toggle="collapse" href="#addNewMessage" role="button" aria-expanded="false"
   aria-controls="addNewMessage">
    Edit message
</a>
<div class="collapse <#if message??>show</#if>" id="addNewMessage">
    <div class="form-group mt-3">
        <form action="/addNewMessage" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>">
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
                <button class="btn btn-primary" type="submit">Save message</button>
            </div>
        </form>
    </div>
</div>