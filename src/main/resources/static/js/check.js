/**
 * check.js
 */
$(function() {
    // ID 중복확인 버튼 클릭 시
    $("#checkDuplicateId").on("click", function() {
        const userId = $("#userId").val(); // 입력된 아이디 값
        if (!userId) {
            $("#idCheck").text("아이디를 입력해주세요.").css("color", "red");
            return;
        }

        $.ajax({
            type: "post",
            url: "/login/userIdCheck",
            data: { "userId": userId },
            dataType: "text",
            success: function(result) {
                if (result == "1") {
                    $("#idCheck").text("사용중인 아이디입니다.").css("color", "red");
                } else {
                    $("#idCheck").text("사용가능한 아이디입니다.").css("color", "blue");
                }
            },
            error: function() {
                alert("서버 오류");
            }
        });
    });

    // 이메일 중복확인: 입력값 변경 시 동작
    $("#userEmail").on("change keyup", function() {
        const userEmail = $("#userEmail").val(); // 입력된 이메일 값
        if (!userEmail) {
            $("#emailCheck").text("이메일을 입력해주세요.").css("color", "red");
            return;
        }

        $.ajax({
            type: "post",
            url: "/checkRest/userEmailCheck",
            data: { "userEmail": userEmail },
            dataType: "text",
            success: function(result) {
                if (result == "1") {
                    $("#emailCheck").text("사용중인 이메일입니다.").css("color", "red");
                } else {
                    $("#emailCheck").text("사용가능한 이메일입니다.").css("color", "blue");
                }
            },
            error: function() {
                alert("서버 오류");
            }
        });
    });
});
