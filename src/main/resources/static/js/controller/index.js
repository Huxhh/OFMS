app.controller('index', ['$scope', function ($scope) {
	$scope.sign = false;//用户登录注册框
	$scope.trans = null;//注册或者登录
	$scope.filmSearchName = null;//搜索电影名称
	$scope.classShow = false;//分类显示
	$scope.$on('classify_change', function (event, data) {
		$scope.classShow = data;
	})
	$scope.classifyKind = ['纪录片', '剧情', '真人秀', '同性', '动画', 
	'动作', '喜剧', '爱情', '科幻', '奇幻', '悬疑', '恐怖', '惊悚', 
	'犯罪', '冒险', '情色', '脱口秀', '运动', '戏曲', '传记', '历史',
	'战争', '古装', '短片', '家庭', '音乐', '西部']
	//classify index, num
	$scope.isActive = null;
	//user
	$scope.user = {
		UserName: null,
		UserPswd: null,
		Email: null,
		Verify: null
	}
	$scope.showName = sessionStorage.getItem('userName');
	$scope.verifyFlag = false;
	$scope.sendEmailValue = '发送验证码';
	$scope.ifCanSendAgain = true;
	//忘记密码
	$scope.forget = {
		Email: null,
		Verify: null,
		NewPassword: null,
		AgainPassword: null
	}
	$scope.forgetPassword = 0;
}]);
app.directive('indexDirective', ['$state', 'request', function ($state, request) {
	return {
		restrict: 'AE',
		link: function (scope, ele, attrs) {
			scope.$on('$stateChangeStart', function 
				(event, toState, toParams, fromState, fromParams) {
					if (toState.name != 'search') {
						scope.isActive = null;
					} else {
						scope.isActive = sessionStorage.getItem('filmKindIndex');
					}
				})
			scope.searchByName = function () {
				if (scope.filmSearchName) {
					sessionStorage.setItem('filmName', scope.filmSearchName);
					sessionStorage.setItem('getOneBy', 'name');
					$state.go('search');
					scope.$broadcast('search_click', null);
				}
			}
			scope.searchByKind = function (kind, index) {
				scope.isActive = index;
				sessionStorage.setItem('filmKind', kind);
				sessionStorage.setItem('filmKindIndex', index);
				sessionStorage.setItem('getOneBy', 'kind');
				$state.go('search');
				scope.$broadcast('search_click', null);
			}
			scope.keyupSearch = function (e) {
	            var keycode = window.event ? e.keyCode : e.which;
				if (keycode == 13) {
					scope.searchByName();
				}
			}
			scope.signupOne = function () {
				if (scope.user.UserName && scope.user.UserPswd) {
					scope.verifyFlag = true;
				} else {
					request.pop_up('必须填写完整');
				}
			}
			scope.signupTwo = function () {
				if (scope.user.Verify && scope.user.Email) {
					request.post('/usr/regist', {
							userName: scope.user.UserName,
							mail: scope.user.Email,
							password: scope.user.UserPswd,
							verNum: scope.user.Verify
					}, function (res) {
						if (res.code == 0) {
							scope.sign = false;
							scope.verifyFlag = false;
							scope.ifCanSendAgain = true;
							scope.verifyFlag = false;
							scope.sendEmailValue = '发送验证码';
							clearInterval(tiem_run);
						}
						request.pop_up(res.msg);
					})
				} else {
					request.pop_up('邮箱和验证码必须填写完整');
				}
			}
			scope.back = function () {
				scope.ifCanSendAgain = true;
				scope.verifyFlag = false;
				scope.sendEmailValue = '发送验证码';
				clearInterval(tiem_run);
			}
			var time = 60;
			var tiem_run, data;
			scope.sendEmail = function (type) {
				switch(type) {
					case 0:
						data = scope.forget.Email;
						break;
					case 1:
						data = scope.user.Email;
						break;
					default:
						return false;
				}
				if (data) {
					if (scope.ifCanSendAgain) {
						time = 60;
						scope.ifCanSendAgain = false;
						request.post('/usr/sendMail', {
							receiveMail: data
						}, function (res) {
							if (res.code == 0) {
								tiem_run = setInterval(function () {
									if (time != 0) {
										scope.sendEmailValue = time + 's';
										time --;
										scope.$apply();
									} else {
										scope.ifCanSendAgain = true;
										scope.sendEmailValue = '发送验证码';
										clearInterval(tiem_run);
									}
								}, 1000);
							} else {
								request.pop_up(res.msg);
							}
						})
					} else {
						return false;
					}
				} else {
					request.pop_up('邮箱必须正确填写');
				}
			}
			scope.signin = function () {
				if(scope.user.UserName && scope.user.UserPswd) {
					request.post('/usr/login', {
						userName : scope.user.UserName,
						password : scope.user.UserPswd
					}, function (res) {
						if (res.code == 0) {
							scope.showName = scope.user.UserName;
							sessionStorage.setItem('userName', scope.showName);
							scope.sign = false;
						} else {
							request.pop_up(res.msg);
						}
					})
				}
			}
			//忘记密码
			scope.forgetPasswordOne = function () {
				console.log(scope.forget)
				if (scope.forget.Email && scope.forget.Verify) {
					request.post('url', {
						data: ''
					}, function (res) {
						if (res.code == 0) {
							scope.forgetPassword = 2;
						} else {
							request.pop_up(res.msg);
						}
					})
				} else {
					request.pop_up('必须填写完整');
				}
			}
			scope.forgetPasswordTwo = function () {
				if (scope.forget.NewPassword && scope.forget.AgainPassword) {
					request.post('url', {
						data: ''
					}, function (res) {
						if (res.code == 0) {
							scope.forgetPassword = 0;
						} else {
							request.pop_up(res.msg);
						}
					})
				} else {
					request.pop_up('必须填写完整');
				}
			}
			scope.logout = function () {
				request.post('/usr/uesrquit', null, function (res) {
					if (res.code == 0) {
						sessionStorage.removeItem('userName');
						scope.showName = null;
					}
					request.pop_up(res.msg);
				})
			}
			//进入用户空间
			scope.gotoUser = function () {
				 scope.$broadcast('enter_user', 1);
				 $state.go('user');
			}
			// window.onresize = function () {
			// 	request.resize();
			// }
		}
	}
}])