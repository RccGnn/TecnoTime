// js/youtubeguide-lazy.js

let playerLoaded = false;

function loadYouTubePlayer() {
	if (playerLoaded) return;
	playerLoaded = true;

	// Carica script API YouTube
	const tag = document.createElement('script');
	tag.src = "https://www.youtube.com/player_api";
	const firstScript = document.getElementsByTagName('script')[0];
	firstScript.parentNode.insertBefore(tag, firstScript);
}

// Funzione richiamata quando la YouTube API è pronta
let player;
function onYouTubePlayerAPIReady() {
	player = new YT.Player('ytplayer-placeholder', {
		height: '360',
		width: '640',
		videoId: 'DC-Xn2C_L1U',
		playerVars: {
			autoplay: 1,
			controls: 1,
			rel: 0,
			modestbranding: 1
		}
	});
}

// Lazy loading con IntersectionObserver
const observer = new IntersectionObserver(entries => {
	entries.forEach(entry => {
		if (entry.isIntersecting) {
			loadYouTubePlayer();
			observer.disconnect(); // non serve più
		}
	});
}, {
	rootMargin: '0px 0px 200px 0px' // carica un po' prima
});

observer.observe(document.getElementById('yt-player-wrapper'));
