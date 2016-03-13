// Karma configuration
module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    //basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine'],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      '../bower_components/angular/angular.js',
      '../bower_components/angular-mocks/angular-mocks.js',
      '../bower_components/ui-router/release/angular-ui-router.js',
      '../bower_components/angular-animate/angular-animate.js',
      '../bower_components/fullcalendar/fullcalendar.js',
      '../bower_components/angular-ui-calendar/src/calendar.js',
      '../bower_components/Chart.js/Chart.js',
      '../bower_components/angular-chart.js/public/angular-chart.js',
      '../bower_components/d3/d3.js',
      '../bower_components/c3/c3.js',
      '../bower_components/c3-angular/c3js-directive.js',
      '../bower_components/angular-growl/build/angular-growl.js',
      '../bower_components/angular-loading-bar/build/loading-bar.js',
      '../bower_components/angular-growl-notifications/public/angular-growl-notifications.js',
      '../bower_components/rangy/rangy-core.js',
      '../bower_components/rangy/rangy-classapplier.js',
      '../bower_components/rangy/rangy-highlighter.js',
      '../bower_components/rangy/rangy-selectionsaverestore.js',
      '../bower_components/rangy/rangy-serializer.js',
      '../bower_components/rangy/rangy-textrange.js',
      '../bower_components/textAngular/src/textAngular.js',
      '../bower_components/textAngular/src/textAngular-sanitize.js',
      '../bower_components/textAngular/src/textAngularSetup.js',
      '../bower_components/perfect-scrollbar/js/perfect-scrollbar.js',
      '../bower_components/classie/classie.js',
      '../bower_components/angular-progress-button-styles/public/angular-progress-button-styles.min.js',
      '../bower_components/angular-translate/angular-translate.js',
      '../bower_components/angular-translate-loader-url/angular-translate-loader-url.js',
      '../bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
      // endbower
      '../app/scripts/**/*.js',
      //'../test/mock/**/*.js',
      '../test/spec/**/*.js'
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      'PhantomJS'
    ],

    // Which plugins to enable
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine'
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
