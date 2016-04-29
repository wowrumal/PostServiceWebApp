<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

    <script type="text/javascript">
        function func1() {
            var address = document.getElementById('pack_address');
            var usersList = document.getElementById('getter');
            var selectedUser = usersList.options[usersList.selectedIndex].value;

            var ownUserId = "${user.id}";

            var arr = [];

            <c:forEach var="userr" varStatus="status" items="${users}">
                var userId = "${userr.id}";
                if (userId != ownUserId) {
                    arr[${status.index}] = [];
                    arr[${status.index}][0] = "${userr.id}";
                    arr[${status.index}][1] = "${userr.passport.address}";
                }
            </c:forEach>

            arr.forEach(function(item, i, arr){
                if (item[0] == selectedUser){
                    address.value = item[1];
                }
            });
        }
    </script>


<div class="container">
    <h3>Посылка</h3>

    <form action="controller" name="package_form" enctype="multipart/form-data" accept-charset="UTF-8" method="post">

        <div class="row">
            <div class="input-field col s6">
                <select required size="1" name="package_type">
                    <option>Письмо</option>
                    <option>Бандероль</option>
                    <option>Крупногабаритная</option>
                </select>
                <label>Тип посылки</label>
            </div>

            <div class="input-field col s6">
                <select id="getter" required size="1" onchange="func1()" name="package_getter_name">
                    <c:choose>
                        <c:when test="${empty users}">
                            <option selected
                                    value="${packagee.getterUser.id}">${packagee.getterUser.secondName} ${packagee.getterUser.firstName}</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="getter_user" items="${users}">
                                <c:choose>
                                    <c:when test="${packagee.getterUser.id==getter_user.id}">
                                        <option selected
                                                value="${getter_user.id}">${getter_user.secondName} ${getter_user.firstName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${user.id != getter_user.id}">
                                            <option value="${getter_user.id}">${getter_user.secondName} ${getter_user.firstName}</option>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <label>Получатель</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input type="text" required name="package_sender_name" id="sender" value="${user.secondName} ${user.firstName}"
                       placeholder="Стасюкевич С.Ю." maxlength="45" class="validate">
                <label for="sender">Отправитель</label>
            </div>

            <div class="input-field col s6">
                <input required type="text" id="pack_address" name="package_address" value="${packagee.address}"
                       placeholder="г. Гродно, ул. Гастелло 17, кв. 1" maxlength="45" class="validate">
                <label for="pack_address">Адрес посылки</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input required type="number" min="1" name="package_post_index" value="${packagee.postIndex}" placeholder="33524"
                       maxlength="7" id="index" class="validate">
                <label for="index">Почтовый индекс</label>
            </div>
        </div>

        <div class="row right">
            <form>
                <button class="btn waves-effect waves-light light-blue lighten-1" type="submit" name="action" onClick="history.go(-1);return true;">
                    <i class="material-icons">arrow_back</i>
                </button>
            </form>

            <c:if test="${user.userRole == 'ADMIN'}">
                <c:if test="${not empty packagee}">
                    <input type="hidden" name="package_id" value="${packagee.idPackage}">
                    <input type="hidden" name="command" value="update_package">
                    <button class="btn waves-effect waves-light" type="submit" name="action">
                        <i class="material-icons">check</i>
                    </button>
                </c:if>
            </c:if>

            <c:if test="${empty packagee}">
                <input type="hidden" name="command" value="add_package">
                <button class="btn waves-effect waves-light" type="submit" name="action">
                    <i class="material-icons">check</i>
                </button>
            </c:if>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp" />

