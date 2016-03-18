'use strict';

/**
 * @ngdoc overview
 * @name ChartShare
 * @description
 * # ChartShare
 *
 * Main module of the application.
 */
window.app_version = 1.9;

angular
  .module('chart-share', [
    'ui.router',
    'ngAnimate',
    'ui.calendar',
    'chart.js',
    'textAngular',
    'gridshore.c3js.chart',
    'angular-growl',
    'growlNotifications',
    'angular-loading-bar',
    'angular-progress-button-styles',
    'pascalprecht.translate',
    'ui.bootstrap'
  ])

  .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
    cfpLoadingBarProvider.latencyThreshold = 5;
    cfpLoadingBarProvider.includeSpinner = false;
  }])
  .config(function (progressButtonConfigProvider) {
    progressButtonConfigProvider.profile('login', {
      style: 'shrink',
      direction: 'vertical'
    });
  })
  .config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
      prefix: 'languages/',
      suffix: '.json'
    });
    $translateProvider.preferredLanguage('en');

  })
  .config(function ($stateProvider, $urlRouterProvider) {


    $urlRouterProvider.when('/dashboard', '/dashboard/home');
    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('base', {
        abstract: true,
        url: '',
        templateUrl: 'views/base.html?v=' + window.app_version,
        controller: 'DashboardCtrl',
        authenticate: true
      })
      .state('login', {
        url: '/login',
        parent: 'base',
        templateUrl: 'views/pages/login.html?v=' + window.app_version,
        controller: 'LoginCtrl',
        authenticate: false
      })
      .state('signup', {
        url: '/signup',
        parent: 'base',
        templateUrl: 'views/pages/signup.html?v=' + window.app_version,
        controller: 'LoginCtrl',
        authenticate: false
      })
      .state('404-page', {
        url: '/404-page',
        parent: 'base',
        templateUrl: 'views/pages/404-page.html?v=' + window.app_version,
        authenticate: false
      })
      .state('dashboard', {
        url: '/dashboard',
        parent: 'base',
        templateUrl: 'views/layouts/dashboard.html?v=' + window.app_version,
        authenticate: false
      })
      .state('home', {
        url: '/home',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/home.html?v=' + window.app_version,
        controller: 'HomeCtrl',
        authenticate: true
      })
      .state('typography', {
        url: '/typography',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/typography.html?v=' + window.app_version
      })
      .state('grid', {
        url: '/grid',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/grid.html?v=' + window.app_version
      })
      .state('physicians', {
        url: '/physicians',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/physicians.html?v=' + window.app_version
      })
      .state('addphysician', {
        url: '/addphysician',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/addPhysician.html?v=' + window.app_version
      })
      .state('profile', {
        url: '/profile',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/profile.html?v=' + window.app_version
      })
      .state('elements', {
        url: '/form/elements',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/forms/elements.html?v=' + window.app_version
      })
      .state('components', {
        url: '/form/components',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/forms/components.html?v=' + window.app_version
      })

      .state('button', {
        url: '/ui-interface/button',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/button.html?v=' + window.app_version
      })

      .state('dropdown', {
        url: '/ui-interface/dropdown',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/dropdown.html?v=' + window.app_version
      })
      .state('other-elements', {
        url: '/ui-interface/other-elements',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/other-elements.html?v=' + window.app_version
      })
      .state('icons', {
        url: '/ui-interface/icons',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/icons.html?v=' + window.app_version
      })
      .state('panels', {
        url: '/ui-interface/panels',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/panel.html?v=' + window.app_version
      })
      .state('alerts', {
        url: '/ui-interface/alerts',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/alert.html?v=' + window.app_version,
        conntroller: 'AlertDemoCtrl'
      })
      .state('progressbars', {
        url: '/ui-interface/progressbars',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/progressbar.html?v=' + window.app_version,
        conntroller: 'ProgressDemoCtrl'
      })
      .state('pagination', {
        url: '/ui-interface/pagination',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/ui-elements/pagination.html?v=' + window.app_version,
        conntroller: 'PaginationDemoCtrl'
      })
      .state('chartjs', {
        url: '/charts/chart.js',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/charts/chartjs.html?v=' + window.app_version
      })
      .state('c3chart', {
        url: '/charts/c3chart',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/charts/c3chart.html?v=' + window.app_version
      })
      .state('calendar', {
        url: '/calendar',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/calendar.html?v=' + window.app_version
      })
      .state('invoice', {
        url: '/invoice',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/invoice.html?v=' + window.app_version
      })
      .state('inbox', {
        url: '/mail/inbox',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/mail/inbox.html?v=' + window.app_version
      })
      .state('compose', {
        url: '/mail/compose',
        parent: 'dashboard',
        templateUrl: 'views/pages/dashboard/mail/compose.html?v=' + window.app_version
      });
  }).run(['$rootScope', '$state', 'authService', function ($rootScope, $state, authService) {
  $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
    console.log("you are in run");
    //debugger;
    var isAuthenticated;
    authService.isAuthenticated().then(function(data){
      console.log("response from isAuthenticated is", data);
      isAuthenticated = data;
    }, function(error){
      console.log("error calling isAuthenticated");
      isAuthenticated = false;
    });
    if (toState.authenticate && !isAuthenticated){
      // User isn’t authenticated
      console.log('you are not authenticated - logging in')
      $state.transitionTo("login");
      event.preventDefault();
    } else if(authService.isAuthenticated()) {
      console.log("Authenticated  - going to Dashboard", $rootScope.user);

    }
  });
}]);
