app.controller('user', ['$scope', '$location', function ($scope, $location) {
	//电影信息
	$scope.userAbout = [];
	//用户信息
	$scope.userInfo = {};
	//修改余额
	$scope.rechargeNum = null;
	//修改密码
	$scope.password = null;
	//查看三种分类电影
	$scope.chooseFilmFlag = 3;
	$scope.$on('enter_user', function (event, data) {
		$scope.chooseFlag = '/user/film';
	})
	//左侧菜单选择
	$scope.chooseFlag = $location.path();
	//打分, 只用作初始值, 不做双向数据绑定
	$scope.scoreNum = 0;
}]);
app.directive('userDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.$emit('classify_change', false);
			request.get('/usr/getUserinfo', function (res) {
				if (res.code == 0) {
					scope.userInfo = res.body.user;
				} else {
					request.pop_up(res.msg);
				}
			})
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
							if (type == 1) {
								request.get('/usr/filmscore', function (res) {
									if (res.code == 0) {
										console.log(res)
									}
								})
							}
						}
					} else {
						request.pop_up(res.msg);
					}
				})
			}
			scope.getFilm(3);
			scope.score = function (scoreNum, index) {
				if (scoreNum == 0) {
					request.pop_up('不能为0');
					return false;
				} else {
					request.post('/user/scorefilm', {
						uid: '1',
						fid: scope.userAbout[index].id.toString(),
						score: scoreNum.toString()
					}, function (res) {
						if (res.code == 0) {
							scope.getFilm(2);
						} else {
							request.pop_up(res.msg);
						}
					})
				}
			}
		}
	}
}]);
app.directive('userCountDirective', ['request', function (request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.recharge = function () {
				if (scope.rechargeNum) {
					request.post('/usr/updateBalance', {
						balance: scope.rechargeNum.toString()
					}, function (res) {
						if (res.code == 0) {
							scope.userInfo.balance = scope.rechargeNum;
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
			scope.changePassword = function () {
				if (scope.password) {
					request.post('/usr/updateProfile', {
						password: scope.password
					}, function (res) {
						if (res.code == 0) {
							scope.logout();
						} else {
							request.pop_up(res.msg);
						}
					})
				} else {
					request.pop_up('不能为空');
				}
			}
		}
	}
}])