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
		UserPswd: null
	}
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
			scope.keyup = function (e) {
	            var keycode = window.event ? e.keyCode : e.which;
				if (keycode == 13) {
					scope.searchByName();
				}
			}
			scope.register = function () {
				if (scope.user.UserPswd && scope.user.UserName) {
					req
				}
			}
		}
	}
}])