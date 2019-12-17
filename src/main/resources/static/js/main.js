$(document).ready(function () {
    $(".btnPost").click(function (e) {
        e.preventDefault();
        console.log(123);
        var button_value=$(this).val()
        $.ajax({
            type: "POST",
            url: "/app/products/cart/add",
            data: {
                index: button_value

            },
            success: function (result) {

                updateCounter();
            },
            error: function (msg) {
                alert("Oops");
            }
        });
    });

    function updateCounter() {
        $.ajax({
            type: "POST",
            url: "/app/products/cart/countRequest",
            success: function (result) {
                alert("запуск функции updateCounter");
                console.log(result);
                $('#lblCounter').text('Товаров в корзине: ' + result);
            }
        });
    }
});