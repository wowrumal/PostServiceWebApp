<%--
  Created by IntelliJ IDEA.
  User: Кирилл
  Date: 4/26/2016
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

</div> <!-- End wrapper-->

<footer class="page-footer blue darken-1">
  <div class="footer-copyright">
    <div class="container center">
      © 2016 Паштовы сервис
    </div>
  </div>
</footer>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>

<script>
  $( document ).ready(function(){
    $(".button-collapse").sideNav();
    $('select').material_select();
    $('.parallax').parallax();
    $('.datepicker').pickadate({
      selectMonths: true, // Creates a dropdown to control month
      selectYears: 15 // Creates a dropdown of 15 years to control year
    });
  });
</script>

</body>
</html>
