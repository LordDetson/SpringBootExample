<#include "security.ftl">

<#macro login path isRegitrationForm>
    <form action="${path}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-4">
                <input class="form-control-plaintext" type="text" name="username" placeholder="Username"/>
            </div>
        </div>
        <#if isRegitrationForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-4">
                    <input class="form-control-plaintext" type="email" name="email" placeholder="some@some.com"/>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-4">
                <input class="form-control-plaintext" type="password" name="password" placeholder="Password"/>
            </div>
        </div>
        <#if !isRegitrationForm>
            <a href="/registration">Registration page</a>
        </#if>
        <button class="btn btn-primary mb-2" type="submit">
            <#if isRegitrationForm>
                Create
            <#else>
                Sign In
            </#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary mb-2" type="submit">Sign Out</button>
    </form>
</#macro>