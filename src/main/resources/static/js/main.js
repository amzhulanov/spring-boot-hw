$(document).ready(function () {
    $(".btnPost").click(function (e) {
        e.preventDefault();
        console.log(123);
        var button_value=$(this).val();
        console.log($(this).val());
        $.ajax({
            type: "GET",
            url: "/app/products/cart/add/"+button_value,

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

            type: "GET",
            url: "/app/products/cart/countRequest",
            success: function (result) {
                console.log(result);
                $('#lblCounter').text('Товаров в корзине: ' + result);
            }
        });
    }

    $(".btnReg").click(function (e) {
        e.preventDefault();
        console.log(12);
        var button_value=$(this).val();
        console.log($(this).val());
        $.ajax({
            type: "GET",
            url: "/user/add/"+button_value,

            success: function (result) {
                console.log(123);
            },

            error: function (msg) {
                alert("Oops");
            }
        });
    });
});