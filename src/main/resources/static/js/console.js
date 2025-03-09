window.onload = function() {
    EJS_player = '#jogo';
    
    // Can also be mgba
    EJS_core = 'gba';
    
    // URL to BIOS file
    EJS_biosUrl = '';

    // Set language based on user's location
    EJS_language = navigator.language || navigator.userLanguage;

    EJS_loadStateURL = 'js/load.state'

    EJS_defaultOptions = {
        'save-state-slot': 1,
        'save-state-location': 'server',
        'save-state-location-url': '/emu/save-state',
        'load-state-location-url': '/emu/load-state'
    }
    
    // URL to Game rom
    EJS_gameUrl = 'js/pokemon.zip';
    
    // Path to the data directory
    EJS_pathtodata = 'js/emulatorjs.min/data/';

    EJS_alignStartButton = "center"

    // Detect mobile device and set fullscreen on load
    if (/Mobi|Android/i.test(navigator.userAgent)) {
        EJS_fullscreenOnLoaded = true;
    } else {
        EJS_startOnLoaded = true;
    }

    var script = document.createElement('script');
    script.src = '/js/emulatorjs.min/data/loader.js';
    document.body.appendChild(script);
}

document.addEventListener('fullscreenchange', function() {
    var gameContainer = document.getElementById('jogo');
    if (document.fullscreenElement) {
        gameContainer.classList.add('fullscreen');
    } else {
        gameContainer.classList.remove('fullscreen');
    }
});
