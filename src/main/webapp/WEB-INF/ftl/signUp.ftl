<#import "/WEB-INF/ftltags/layout.ftl" as layout>
<#import "/WEB-INF/ftltags/navbar.ftl" as navbar>

<@layout.template>
    <@navbar.navbar/>
<div class="container col-lg-6 col-xl-6 col-md-7 col-sm-8 col-xs-8 shadow-lg p-3 mb-5 bg-white rounded"
     style="margin-top: 30px; border-radius: 15px"
     align="center">
    <div class='container-fluid' align="center">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item nav-item-dark">
                <a class="nav-link active" id="signIn-tab" data-toggle="tab" href="#signIn" role="tab"
                   aria-controls="signIn" aria-selected="true">Log In</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="signUp-tab" data-toggle="tab" href="#signUp" role="tab"
                   aria-controls="signUp" aria-selected="false">Sign Up</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="signIn" role="tabpanel" aria-labelledby="signIn-tab">
                <h2>Authentication</h2>
                <form method="post" action="signIn" id="login">
                    <div class="form-group">
                        <label for='e-mail'>Email</label>
                        <input type='email' class="form-control" name='e-mail' placeholder='Email' id='e-mail'
                               required>
                    </div>
                    <div class="form-group">
                        <label for='password'>Password</label>
                        <input type='password' class="form-control" name='password' placeholder='Password'
                               id='password' minlength="6"  maxlength="12" required>
                    </div>
                    <div class="form-group">
                        <label for="remember">Remember me</label>
                        <input type="checkbox" id="remember" name="remember">
                    </div>
                    <button type="submit" class="btn btn-dark" style="margin-bottom: 10px">Sign In</button>
                </form>
                <div class="row" align="center" style="justify-content: center">
                    <a href="https://vk.com" style="padding-right: 5px">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 24 24">
                            <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6.344 16.163h-1.867c-1.055 0-1.232-.601-2.102-1.469-.785-.785-1.22-.183-1.202.935.006.297-.141.534-.495.534-1.105 0-2.694.156-4.304-1.58-1.647-1.779-3.374-5.348-3.374-5.699 0-.208.172-.301.459-.301h1.898c.503 0 .545.249.686.568.584 1.331 1.981 4.002 2.354 2.511.214-.856.301-2.839-.615-3.01-.52-.096.396-.652 1.722-.652.33 0 .688.035 1.054.12.673.156.676.458.666.898-.034 1.666-.235 2.786.204 3.069.419.271 1.521-1.502 2.104-2.871.159-.378.191-.632.643-.632h2.322c1.216 0-.159 1.748-1.21 3.112-.847 1.099-.802 1.12.183 2.034.701.651 1.53 1.54 1.53 2.043 0 .238-.186.39-.656.39z"></path>
                        </svg>
                    </a>
                    <a href="https://twitter.com" style="padding-right: 5px">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 24 24">
                            <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6.066 9.645c.183 4.04-2.83 8.544-8.164 8.544-1.622 0-3.131-.476-4.402-1.291 1.524.18 3.045-.244 4.252-1.189-1.256-.023-2.317-.854-2.684-1.995.451.086.895.061 1.298-.049-1.381-.278-2.335-1.522-2.304-2.853.388.215.83.344 1.301.359-1.279-.855-1.641-2.544-.889-3.835 1.416 1.738 3.533 2.881 5.92 3.001-.419-1.796.944-3.527 2.799-3.527.825 0 1.572.349 2.096.907.654-.128 1.27-.368 1.824-.697-.215.671-.67 1.233-1.263 1.589.581-.07 1.135-.224 1.649-.453-.384.578-.87 1.084-1.433 1.489z"></path>
                        </svg>
                    </a>
                    <a href="https://facebook.com" style="padding-right: 5px">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 24 24">
                            <path d="M12 0c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm3 8h-1.35c-.538 0-.65.221-.65.778v1.222h2l-.209 2h-1.791v7h-3v-7h-2v-2h2v-2.308c0-1.769.931-2.692 3.029-2.692h1.971v3z"></path>
                        </svg>
                    </a>
                    <a href="https://instagram.com" style="padding-right: 5px">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 24 24">
                            <path d="M14.829 6.302c-.738-.034-.96-.04-2.829-.04s-2.09.007-2.828.04c-1.899.087-2.783.986-2.87 2.87-.033.738-.041.959-.041 2.828s.008 2.09.041 2.829c.087 1.879.967 2.783 2.87 2.87.737.033.959.041 2.828.041 1.87 0 2.091-.007 2.829-.041 1.899-.086 2.782-.988 2.87-2.87.033-.738.04-.96.04-2.829s-.007-2.09-.04-2.828c-.088-1.883-.973-2.783-2.87-2.87zm-2.829 9.293c-1.985 0-3.595-1.609-3.595-3.595 0-1.985 1.61-3.594 3.595-3.594s3.595 1.609 3.595 3.594c0 1.985-1.61 3.595-3.595 3.595zm3.737-6.491c-.464 0-.84-.376-.84-.84 0-.464.376-.84.84-.84.464 0 .84.376.84.84 0 .463-.376.84-.84.84zm-1.404 2.896c0 1.289-1.045 2.333-2.333 2.333s-2.333-1.044-2.333-2.333c0-1.289 1.045-2.333 2.333-2.333s2.333 1.044 2.333 2.333zm-2.333-12c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6.958 14.886c-.115 2.545-1.532 3.955-4.071 4.072-.747.034-.986.042-2.887.042s-2.139-.008-2.886-.042c-2.544-.117-3.955-1.529-4.072-4.072-.034-.746-.042-.985-.042-2.886 0-1.901.008-2.139.042-2.886.117-2.544 1.529-3.955 4.072-4.071.747-.035.985-.043 2.886-.043s2.14.008 2.887.043c2.545.117 3.957 1.532 4.071 4.071.034.747.042.985.042 2.886 0 1.901-.008 2.14-.042 2.886z"></path>
                        </svg>
                    </a>
                </div>
            </div>
            <div class="tab-pane fade" id="signUp" role="tabpanel" aria-labelledby="signUp-tab">
                <h2>Registration</h2>
                <form method='post' action="signUp" id="reg" name="reg">
                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" name="firstName" placeholder="Enter first name"
                               id="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" name="lastName" placeholder="Enter last name"
                               id="lastName" required>
                    </div>
                    <div class="form-group">
                        <label for='email'>Email</label>
                        <input type='email' class="form-control" name='email' aria-describedby="emailHelp"
                               placeholder='Enter email' id='email'
                               required>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                            else.
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="passwordFirst">Password</label>
                        <input type="password" class="form-control" name="passwordFirst"
                               placeholder="Enter password"
                               id="passwordFirst" minlength="6" maxlength="12" required>
                    </div>
                    <div class="form-group">
                        <label for="passwordSecond">Password</label>
                        <input type="password" class="form-control" name="passwordSecond"
                               placeholder="Repeat password"
                               id="passwordSecond" minlength="6" maxlength="12" required>
                    </div>
                    <div style="margin: 25px">
                        <button type="submit" class="btn btn-dark" id="submit">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/check.js"></script>
</@layout.template>