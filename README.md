# dnaAnalysis
Analyse a given DNA in order to determine if is Mutant or Human. 

## Accessing the API
In order to access the api, please use the following path: [App Link](https://dna-analysis.herokuapp.com/)

## Analyzing a DNA
To analyzie a DNA to determine that the DNA is either `Human` or `Mutant` send a **_POST_** request to the [API](https://dna-analysis.herokuapp.com/mutant/). The response will be a `HTTP 200 CODE` if the DNA is mutant and a `HTTP 403 CODE` if the DNA is not mutant. An example is given bellow:

```json
{
"dna":["GTGCCA","CAGTGC","TTATGT","AGAAGG","CCACTA","TCACTG"]
}
```

## Retrieving the stats
After submitting DNA samples to analyses, it's possible to check how samples were determined as `Human` or as `Mutant`. Please refer to the [Stats Link](https://dna-analysis.herokuapp.com/stats/) with a **_GET_** request. The result will be displayed like the following example:

```json
{
    "count_mutant_dna": 4,
    "count_human_dna": 10,
    "ratio": 0.4
}
```

## Accessing the DNA inputed
An additional API was created in order to access the DNAs inputeds and recorded in the Database. The result will be a List of the DNAs, with its Genes and the marker `isMutant`. Please refer to the [Collection Link](https://dna-analysis.herokuapp.com/mutant/) with a **_GET_** request. The result will be displayed like the following example:
```json
[{"id":1,"genes":["GTGCCA","CAGTGC","TTATGT","AGAAGG","CCACTA","TCACTG"],"isMutant":true},{"id":2,"genes":["GTGCCA","CAGTGC","TTGTGT","AGAAGG","CCACTA","TCACTG"],"isMutant":false}]
```
