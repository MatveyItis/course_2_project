<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:layout title="Profile">
    <t:navbar/>
    <div class="col-10" style="width: 100%; height: 100%">
        <div class="row">
            <div class="col-3">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list"
                       href="#list-profile" role="tab" aria-controls="profile">Profile</a>
                    <a class="list-group-item list-group-item-action" id="list-friends-list" data-toggle="list"
                       href="#list-friends" role="tab" aria-controls="friends">Friends</a>
                    <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list"
                       href="#list-messages" role="tab" aria-controls="messages">Messages</a>
                    <a class="list-group-item list-group-item-action" id="list-music-list" data-toggle="list"
                       href="#list-music" role="tab" aria-controls="music">Music</a>
                    <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list"
                       href="#list-settings" role="tab" aria-controls="settings">Settings</a>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">

                    </div>
                    <div class="tab-pane fade" id="list-friends" role="tabpanel" aria-labelledby="list-friends-list">

                    </div>
                    <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">

                    </div>
                    <div class="tab-pane fade" id="list-music" role="tabpanel" aria-labelledby="list-music-list">

                    </div>
                    <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">

                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>