<%--
  Created by IntelliJ IDEA.
  User: matveymaletskov
  Date: 03/11/2018
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Library">
    <t:navbar/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action  list-group-item-dark active show"
                       id="list-library-list" data-toggle="list"
                       href="#list-library" role="tab" aria-controls="library">Library
                    </a>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                       id="list-top-list"
                       data-toggle="list"
                       href="#list-top" role="tab" aria-controls="top">Top charts
                    </a>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                       id="list-albums-list"
                       data-toggle="list"
                       href="#list-albums" role="tab" aria-controls="albums">Albums
                    </a>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                       id="list-settings-list"
                       data-toggle="list"
                       href="#list-settings" role="tab" aria-controls="settings">Settings</a>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="list-library" role="tabpanel"
                         aria-labelledby="list-library-list">
                    </div>
                    <div class="tab-pane fade" id="list-top" role="tabpanel" aria-labelledby="list-top-list">

                    </div>
                    <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">

                    </div>
                    <div class="tab-pane fade" id="list-albums" role="tabpanel" aria-labelledby="list-albums-list">

                    </div>
                    <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">

                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>