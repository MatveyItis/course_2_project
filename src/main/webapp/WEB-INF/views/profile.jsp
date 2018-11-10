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

<t:layout title="Profile" style="/css/default.css">
    <div class="container-fluid row" style="padding-top: 20px">
        <div class="col-3">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action  list-group-item-dark active show"
                   id="list-profile-list" data-toggle="list"
                   href="#list-profile" role="tab" aria-controls="profile">Profile
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-friends-list"
                   data-toggle="list"
                   href="#list-friends" role="tab" aria-controls="friends">Friends
                    <span class="badge badge-dark badge-pill" style="">14</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-messages-list"
                   data-toggle="list"
                   href="#list-messages" role="tab" aria-controls="messages">Messages
                    <span class="badge badge-dark badge-pill">28</span>
                </a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-music-list"
                   data-toggle="list"
                   href="#list-music" role="tab" aria-controls="music">Music</a>
                <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-dark"
                   id="list-settings-list"
                   data-toggle="list"
                   href="#list-settings" role="tab" aria-controls="settings">Settings</a>
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="list-profile" role="tabpanel"
                     aria-labelledby="list-profile-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                        <div class="row">
                            <div class="col-4" style="padding: 10px">
                                <div class="container" style="background-color: white;
                                border-radius: 50%;width: 270px;height: 270px;">
                                    <img src="/images/avatarcat.png" alt="avatar" width="250" height="250"
                                         style="border-radius: 50%;margin-top: 10px; margin-left: -5px;">
                                </div>
                            </div>
                            <div class="col-8" style="padding: 10px;">
                                <div class="container"
                                     style="height: 270px; border-radius: 10px;background-color: white">
                                    <h2>${user.getFirstName()} ${user.getLastName()}</h2>
                                    <hr>
                                    <div class="row">
                                        <div class="col-6">Date of birth:</div>
                                        <div class="col-6">11.11.1999</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">City:</div>
                                        <div class="col-6">Kazan</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">Lovely music:</div>
                                        <div class="col-6">Pop, Rap and etc</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding: 10px">
                            <div class="container-fluid" style="background-color: white; border-radius: 10px;">
                                <p>Here is my lovely songs:</p>
                                <p>Дорогие друзья, социально-экономическое развитие требует от нас анализа модели
                                    развития? Соображения высшего порядка, а также новая модель организационной
                                    деятельности влечет за собой процесс внедрения и модернизации системы обучения
                                    кадров, соответствующей насущным потребностям. Равным образом постоянное
                                    информационно-техническое обеспечение нашей деятельности представляет собой
                                    интересный эксперимент проверки существующих финансовых и административных
                                    условий?

                                    Значимость этих проблем настолько очевидна, что курс на
                                    социально-ориентированный национальный проект способствует повышению
                                    актуальности экономической целесообразности принимаемых решений.

                                    Задача организации, в особенности же начало повседневной работы по формированию
                                    позиции требует определения и уточнения существующих финансовых и
                                    административных условий. С другой стороны социально-экономическое развитие
                                    позволяет оценить значение форм воздействия? Таким образом, консультация с
                                    профессионалами из IT обеспечивает актуальность дальнейших направлений развитая
                                    системы массового участия!</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-friends" role="tabpanel" aria-labelledby="list-friends-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                        <p>Разнообразный и богатый опыт реализация намеченного плана развития позволяет выполнить
                            важнейшие
                            задания по разработке экономической целесообразности принимаемых решений. Значимость этих
                            проблем настолько очевидна, что сложившаяся структура организации позволяет выполнить
                            важнейшие
                            задания по разработке новых предложений! Повседневная практика показывает, что рамки и место
                            обучения кадров обеспечивает актуальность направлений прогрессивного развития.

                            Таким образом, постоянное информационно-техническое обеспечение нашей деятельности играет
                            важную
                            роль в формировании соответствующих условий активизации.

                            Значимость этих проблем настолько очевидна, что рамки и место обучения кадров позволяет
                            оценить
                            значение дальнейших направлений развития проекта. С другой стороны повышение уровня
                            гражданского
                            сознания позволяет оценить значение новых предложений. С другой стороны постоянный
                            количественный рост и сфера нашей активности напрямую зависит от дальнейших направлений
                            развитая
                            системы массового участия.

                            Повседневная практика показывает, что новая модель организационной деятельности играет
                            важную
                            роль в формировании форм воздействия. Разнообразный и богатый опыт новая модель
                            организационной
                            деятельности обеспечивает широкому кругу специалистов участие в формировании направлений
                            прогрессивного развития. Таким образом, дальнейшее развитие различных форм деятельности
                            представляет собой интересный эксперимент проверки форм воздействия. Не следует, однако,
                            забывать о том, что рамки и место обучения кадров обеспечивает актуальность соответствующих
                            условий активизации! Задача организации, в особенности же дальнейшее развитие различных форм
                            деятельности способствует повышению актуальности новых предложений? Таким образом, начало
                            повседневной работы по формированию позиции...</p>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
                    <div class="container-fluid" style="background-color:  rgb(198, 200, 202); border-radius: 15px">
                    </div>
                </div>
                <div class="tab-pane fade" id="list-music" role="tabpanel" aria-labelledby="list-music-list">
                    <div class="container-fluid"
                         style="background-color:  rgb(198, 200, 202); border-radius: 15px; padding: 15px;">
                        <div class="container" align="center">
                            <h3>Music</h3>
                            <hr>
                        </div>
                        <c:forEach var="song" items="${userSongs}">
                            <t:audio src="${song.getSongSrc()}" songId="${song.getSongId()}"
                                     songTitle="${song.getTitle()}" isHaving="${song.isHaving()}"/>
                        </c:forEach>
                    </div>
                </div>
                <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
                    <div class="container-fluid" style="background-color: rgb(53,58,73); border-radius: 15px">
                        <div class="header" align="center">
                            <h3 style="color: white">Settings</h3>
                        </div>
                        <div class="container" id="settings-form">
                            <p>You can change your details or fill in if you missed.</p>
                            <form>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputEmail4">Email</label>
                                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputPassword4">Password</label>
                                        <input type="password" class="form-control" id="inputPassword4"
                                               placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="inputAddress">Address</label>
                                        <input type="text" class="form-control" id="inputAddress"
                                               placeholder="1234 Main St">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputDate">Date of Birth</label>
                                        <input type="date" class="form-control" id="inputDate">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputCity">City</label>
                                        <input type="text" class="form-control" id="inputCity">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputState">State</label>
                                        <select id="inputState" class="form-control">
                                            <option selected>Kazan</option>
                                            <option>Moscow</option>
                                            <option>Saint-Petersburg</option>
                                        </select>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-light">Update</button>
                            </form>
                            <a href="#">Delete your account</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:layout>