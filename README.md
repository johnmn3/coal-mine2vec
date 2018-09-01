# coal-mine2vec

Let's do a little spelunking in the coal mines, shall we?

## Description

Uses a forked version of this word2vec implementation https://github.com/Bridgei2i/clojure-word2vec

With Mike Fikes' coal-mine framework for testing the clojurescript compiler.

Builds a word2vec of a quarter-million lines of clojure code.

## Usage

- drop into a `clj` repl in this project
- pull in is-to, which will run the ANN
`(require '[coal-mine2vec.is-to :refer [is-to -main]])`
- now get some relations
`(first (is-to "inc" "dec" "max")) #_=> "min"`

_Thing one is to thing two... as other thing one is to..._

Or you can also run it from the command line:

```
clj -A:is-to map reduce for
"map" is to "reduce" as "for" is probably to "loop"
 ... but may also be one of these: "&" "c]" "coll]" ":as"
```

Might be interesting to semantically mutate existing programs, using this with something like gigasquid's generative, genetic programming tutorials using spec: https://github.com/gigasquid/genetic-programming-spec

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
