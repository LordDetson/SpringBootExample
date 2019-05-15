<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-4">
                <input class="form-control-plaintext" type="text" name="username" placeholder="Username" value="${username}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-4">
                <input class="form-control-plaintext" type="text" name="password" placeholder="Password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-4">
                <input class="form-control-plaintext" type="email" name="email" placeholder="some@some.com"
                       value="${email!''}"/>
            </div>
        </div>
        <button class="btn btn-primary mb-2" type="submit">Save</button>
    </form>
</@c.page>