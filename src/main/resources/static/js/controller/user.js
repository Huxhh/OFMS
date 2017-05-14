app.controller('user', ['$scope', '$location', function ($scope, $location) {
	$scope.userAbout = [];
	//用于余额
	$scope.count = null;
	//修改余额
	$scope.rechargeNum = null;
	//修改密码
	$scope.password = null;
	//修改姓名
	$scope.name = null;
	//查看三种分类电影
	$scope.chooseFilmFlag = 3;
	//左侧菜单选择
	$scope.chooseFlag = $location.path();
}]);
app.directive('userDirective', [function () {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.$emit('classify_change', false);
		}
	}
}]);
app.directive('userFilmDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			var getUrl;
			scope.getFilm = function (type) {
				scope.chooseFilmFlag = type;
				switch(type) {
					case 1:
						getUrl = '/usr/filmjudged';
						break;
					case 2:
						getUrl = '/usr/filmunjudged';
						break;
					case 3:
					default:
						getUrl = '/usr/film';
						break;
				}
				request.get(getUrl, function (res) {
					if (res.code == 0) {
						if (type == 2) {
							scope.userAbout = res.body;
						} else {
							scope.userAbout = res.body.content;
						}
					} else {
						request.pop_up(res.msg);
					}
				})
			}
			scope.getFilm(3);
		}
	}
}]);
app.directive('userCountDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			request.get('url', function (res) {
				if (res.code == 0) {
					scope.count = res.body;
				} else {
					request.pop_up(res.msg);
				}
			})
			scope.recharge = function () {
				if (scope.rechargeNum) {
					request.post('/usr/updateBalance', {
						balance: scope.rechargeNum
					}, function (res) {
						if (res.code == 0) {
							scope.count = scope.rechargeNum;
						}
						request.pop_up(res.msg);
					})
				}
			}
		}
	}
}]);
app.directive('userInfoDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.changeName = function () {
				if (scope.name) {
					request.post('/usr/updateProfile', {
						name: scope.name
					}, function (res) {
						request.pop_up(res.msg);
					})
				} else {
					request.pop_up('不能为空');
				}
			}
			scope.changePassword = function () {
				if (scope.password) {
					request.post('/usr/updateProfile', {
						password: scope.password
					}, function (res) {
						request.pop_up(res.msg);
					})
				} else {
					request.pop_up('不能为空');
				}
			}
		}
	}
}])