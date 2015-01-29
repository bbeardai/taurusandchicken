$(document).ready(function() {
	$("#sign_up_form").validate({
		rules:{
			sign_up_username:{
				required:true,
				email:true,
			},
			sign_up_password:{
				required:true,
				minlength : 5
			},
			sign_up_repassword:{
				required:true,
				equalTo : "#sign_up_password",
			}
		},
		messages:{
			sign_up_username:{
				required:"Please fill in the email",
				email:"Your Account Information is incorrect",
			},
			sign_up_password:{
				required:"Your Account Information is incorrect",
				minlength : "Mush more than 5 characters",
			},
			sign_up_repassword:{
				required:"Please fill in the password",
				equalTo:"Password are not the same",
			}
		},
		showErrors: function(errorMap, errorList) {
          $.each(this.validElements(), function (index, element) {
              var $element = $(element);
			  $element.parent().removeClass('has-error').addClass('has-success');
              $element.data("title", "").tooltip("destroy");
          });
 
          $.each(errorList, function (index, error) {
              var $element = $(error.element);
			  $element.parent().removeClass('has-success').addClass('has-error');
              $element.tooltip("destroy").data("title", error.message).tooltip(); 
          });
        },
	});
	$("#sign_in_form").validate({
		rules:{
			sign_in_username:{
				required:true,
				email:true,
			},
			sign_in_password:{
				required:true,
			}
		},
		messages:{
			sign_in_username:{
				required:"Please fill in the email",
				email:"Your Account Information is incorrect",
			},
			sign_in_password:{
				required:"Your Account Information is incorrect",
			}
		},
		showErrors: function(errorMap, errorList) {
          $.each(this.validElements(), function (index, element) {
              var $element = $(element);
			  $element.parent().removeClass('has-error').addClass('has-success');
              $element.data("title", "").tooltip("destroy");
          });
 
          $.each(errorList, function (index, error) {
              var $element = $(error.element);
			  $element.parent().removeClass('has-success').addClass('has-error');
              $element.tooltip("destroy").data("title", error.message).tooltip(); 
          });
        },
	});
});
$(document).on("focusout","#sign_up_username",function(){
	$.ajax({
		url:"usernamevalidation",
		type:"POST",
		dataType:'json',
		data:{
			"username":$("#sign_up_username").val(),
		},
	})
	.always(function(data){
		if(data.responseText=="invalid"){
			$("#sign_up_username").parent().removeClass('has-success').addClass('has-error');
			$("#sign_up_username").tooltip("destroy").data("title", "Occupied User Name").tooltip(); 
		}
	});
});
