# reactive-markdown

This tiny library renders Markdown strings to React components.

The code is borrowed from Bruce Hauman's amazing
[devcards](https://github.com/bhauman/devcards) with his blessing.

The employed Markdown->HTML converter in both this library and ```devcards```
is [showdownjs](https://github.com/showdownjs/showdown).

There are is one function only in the ```reactive-markdown.markdown```
namespace that you can use: ```md->react```. It is a variadic function that
accepts strings and returns a React component. Easy peasy.

``` clojure

(defn blog-post []
  (markdown/md->react
   "## Why would you want that?

    Easy, in order to **write** a _blog post_ using:

    1. Reagent
    2. Markdown"))
    
```

For further details, if you really need them, have a look at the ```demo-src```
folder.

## Development Mode

```lein fig-demo``` (will clean it for you)  **or** ```lein figwheel demo```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```lein minify``` **or** ```lein do clean, cljsbuild once min```

## Testing

For testing, you need first of all [PhantomJS](https://github.com/ariya/phantomjs/), after which you can execute:

```lein unit-test``` **or** ```lein do clean, cljsbuild test unit```

## Other Resources

 * [CLJSJS](https://github.com/cljsjs/packages)
