<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Платёж</title>
</head>
<body>

    <h1>Оплата</h1>
    <h2>Информация о платеже:</h2>
    <table>
        <tr>
            <td>Имя плательщика:</td>
            <td>${receipt.clientName}</td>
        </tr>
        <tr>
            <td>Сервис:</td>
            <td>${receipt.serviceName}</td>
        </tr>
        <tr>
            <td>Данные для оплаты:</td>
            <td>${receipt.paymentData}</td>
        </tr>
        <tr>
            <td>Сумма:</td>
            <td>${receipt.cost}</td>
        </tr>
        <tr>
            <td>Дата:</td>
            <td>${receipt.date}</td>
        </tr>
    </table>

    <hr>

    <h2>Введите данные вашей кредитной карты:</h2>
    <form action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="payment_command">
        <table>
            <tr>
                <td>Номер карты:</td>
                <td>
                    <input name="card_number" type="text" required placeholder="1234 4321 2341 2134"
                            pattern="\b(?:3[47]\d{2}([\s-]?)\d{6}\1\d|(?:(?:4\d|5[1-5]|65)\d{2}|6011)([\s-]?)\d{4}\2\d{4}\2)\d{4}\b">
                </td>
            </tr>

            <tr>
                <td>Срок действия (месяц/год):</td>
                <td>
                    <input name="expire_date" type="text" required placeholder="08/19"
                            pattern="^[0-1][1-9]/[0-9][0-9]$">
                </td>
            </tr>
            <tr>
                <td>CVC (на обратной стороне карты):</td>
                <td>
                    <input name="cvc" type="text" required placeholder="231" pattern="^[0-9][0-9][0-9]$">
                </td>
            </tr>
        </table>
        <input type="submit" value="оплатить">
    </form>

</body>
</html>
