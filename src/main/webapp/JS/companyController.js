

var app = angular.module('couponSystem', []);
app.controller('companyController', function($scope, $http, $window, $timeout) {
	
function createCoupon(){
	 document.getElementById("createcoupon").alart('hello');
};

		
		
	
$http.get("rest/company/getComp_name").success(function(response) { $scope.comp_name = response;});
});