<#import "parts/common.ftl" as c/>
<#import "parts/navbar.ftl" as n/>
<#import "/spring.ftl" as spring/>
<@c.template "Edit profile">
    <@n.navbar/>
    <div class="container col-lg-6 col-xl-5 col-md-6" align="center" style="padding-top: 80px">
        <h4>Update info</h4>
        <form action="/edit_profile" method="post" id="update_info_form">
            <@spring.bind "userForm"/>
            <div class="form-group">
                <label for="username">Username</label>
                <@spring.formInput "userForm.username" 'class="form-control" id="username" placeholder="Enter username" required'/>
                <@spring.formInput "userForm.usernameOld" 'hidden'/>
                <br>
                <@spring.showErrors "userForm.username"/>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <@spring.formInput "userForm.email" 'class="form-control" id="email" placeholder="Enter email" required' 'email'/>
                <@spring.formInput "userForm.emailOld" 'hidden'/>
                <br>
                <@spring.showErrors "userForm.email"/>
            </div>
            <div class="form-group">
                <label for="firstName">First name</label>
                <@spring.formInput "userForm.firstName" 'class="form-control" id="firstName" placeholder="Enter first name" '/>
                <br>
                <@spring.showErrors "userForm.firstName"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last name</label>
                <@spring.formInput "userForm.lastName" 'class="form-control" id="lastName" placeholder="Enter last name" '/>
                <br>
                <@spring.showErrors "userForm.lastName"/>
            </div>
            <div class="form-group">
                <label for="aboutMe">Description</label>
                <@spring.formTextarea "userForm.aboutMe" 'class="form-control" id="aboutMe" placeholder="Tell us about yourself"'/>
                <br>
                <@spring.showErrors "userForm.aboutMe"/>
            </div>
            <div class="form-group">
                <label for="newPassword">Password</label>
                <@spring.formPasswordInput "userForm.newPassword" 'class="form-control" id="newPassword" placeholder="Enter new password"'/>
                <br>
                <@spring.showErrors "userForm.newPassword"/>
            </div>
            <div class="form-group">
                <label for="newPassword2">Password</label>
                <@spring.formPasswordInput "userForm.newPassword2" 'class="form-control" id="newPassword2" placeholder="Repeat new password"'/>
                <br>
                <@spring.showErrors "userForm.newPassword2"/>
            </div>
            <button type="submit" class="btn btn-info">Update</button>
        </form>
    </div>
</@c.template>