<#import "parts/common.ftl" as layout>
<#import "parts/navbar.ftl" as navbar>

<@layout.template "Registration">
    <@navbar.navbar/>
    <div class="container col-lg-4 col-xl-4 col-md-5 col-sm-7 col-xs-7 shadow-lg p-3 mb-5 mt-5 bg-white rounded"
         style="margin-top: 30px; border-radius: 15px"
         align="center">
        <div class='container-fluid' align="center">
            <h2>Registration</h2>
            <form method='post' action="/registration" id="reg" name="reg">
                <div class="form-group">
                    <label for='username'>Username</label>
                    <input type='text' class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           name='username'
                           placeholder='Enter username' id='username'
                           value="<#if user.username??>${user.username}<#else></#if>">
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for='email'>Email</label>
                    <input type='email' name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder='Enter email' id='email' value="<#if user.email??>${user.email}<#else></#if>">
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           name="password"
                           placeholder="Enter password"
                           id="password" minlength="6">
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="password2">Password</label>
                    <input type="password" class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           name="password2"
                           placeholder="Repeat password"
                           id="password2" minlength="6">
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="singer">
                        Are you singer?
                    </label>
                    <input type="checkbox" name="singer" id="singer">
                </div>
                <div style="margin: 25px">
                    <button type="submit" class="btn btn-dark" id="submit">Sign Up</button>
                </div>
            </form>
        </div>
    </div>
</@layout.template>