# Theory Intro 


### Input 
X = ( X1, X2, ... ,Xn, C)

X1 = {a, ...,z} -> {x1, ..., x_{1(ri)} }  , ri = length 
C - random const. 


##### Dataset T - training* 
```
X1 X2 X3    ...  XX C
1  2   3          0 0
1  0   0		  0	0 
3  0   0          0 0
```


### Algorithm BNC 


**model B(X,G,Theta)**

> **X** - Input dataset
> **G** - Graph   G(X,E); X=nodes , E=edges 
> ***Theta*** -  BNC  parameter that can be call  as weights or probabilities 



* ***Theta*** = { P(Xi=x_{ik}|{Parents}), .. , (theta_{ijkc}) }*

for each i, or X1,.., Xn:
**theta_{ijkc}** = (Nijkc + N') / (Nijc^{k} + ri \* N') 
**theta_{c}** = (Nc + N')/ (N + ri \* N') 

> N' = 0.5
> Nc - # de valores de C
> N - # linhas de T
> Nijkc - # valores possiveis para a config i^j^
> Nijc^k^ - # valores possiveis que o parent de Nijkc pode ter
> pi_Xi - conjunto de possiveis parents de  Xi em G, excluindo C


```
# Dataset Train - T
# X Y
# 1 3
# 2 2


object = BNC(X,G,Theta)
object.train(T)
Y' = object.predict(X)

# X Y' - Dataset Predicted
# 1 2
# 2 4

score = metric.f1()
evaluation = score(Y_benchmark, Y' )
```
## BNC  to TAN - It's a good approximation!


>   ***Definitions:***
>    TAN approx. to BNC 
>    TAN means Tree argumented Naive Bayes Classifier 

**Flowchart**
*TAN learning w/ Train data (only 1x) ----> TAN predict w/ Test data (many times as you want) ----> TAN score (measure the results)* 

- **`TAN.train(Y_train,X_train)`** 
    1.  read T and convert it into Graph G (using LL or MDL)  
    2.  use PRIM or Kruskal to find/create MWST from G   
    3.  Choose a root from MWST and add the node C

- **`Y_predicted = TAN.predict(X_test)`**

- **`Score.f1(Y_predicted, Y_test)`**


## Code Diagram 

```
|Packet1 - Nodes |
|  +  Node       | ----> import | Pack2 - Data Structure        | 
                                |  + MWST                       |
                                |  + Graph                      |
                    
    (Pack2 )---->import | Pack3 - TAN              |                
                        |   + TAN_T                |----------->| Pack5 - App |
                        |   + Probability          |            |  + BNC      |
                                                                |             |   ---> | Pack6 - GUI            | 
                        |Pack4 - score      |                   |             |        |  # Run                 |    
                        |+ scorefunction    | ----------------->|             |        |                        |
                                                                                       |                        |
                                                | Pack7 - File management | import --->|                        |
                                                |  +read                  |                             
                                                |  +print                 |                          

```











