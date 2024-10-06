self.addEventListener("activate", Event => Handle_activate(Event));
self.addEventListener("fetch", Event => Handle_fetch(Event));
self.addEventListener("install", Event => Handle_install(Event));

function Handle_activate(Event)
{
    console.log(arguments.callee.toString().match(/function ([^\(]+)/)[1] + ' ' + Event);
}

function Handle_fetch(Event)
{
    console.log(arguments.callee.toString().match(/function ([^\(]+)/)[1] + ' ' + Event);
}

function Handle_install(Event)
{
    Event.waitUntil(
        caches.open('init-cache').then((cache) => {
            return cache.addAll([
                '/',
                '/css/style.css',
                '/js/main.js',
                '/assets/logo.png'
            ]);
        })
    );
    console.log(arguments.callee.toString().match(/function ([^\(]+)/)[1] + ' ' + Event);
}