# Theor Intro 


### Input 
X = ( X1, X2, ... ,Xn, C)

X1 = {a, ...,z} -> {x1, ..., x_{1(ri)} }   ri = length 
C - random const. 


**Dataset T - train**  
```
X1 X2 X3    ...  XX C
1  2   3          0 0
1  0   0		  0	0 
3  0   0          0 0
```


### Algorithm BNC 


**model B(X,G,Theta)**

>
> X - input
> G - graph   G(X,E) = X-nodes E-edges 
> Theta -  BNC  parameter



* ***theta** = { P(Xi=x_{ik}|{Parents}), .. , (theta_{ijkc}) }*

**theta_{ijkc}** = NijkcC + N'/ Nijc^{k} + ri N'  -> X1,.., Xn*
**theta_{c}** = Nc + N'/ N + ri N'* 
> N' = 0.5
> Nc - # de valores de C
> N' - 0.5 
> N - # linhas de T
> pi_Xi - conjunto de possiveis parents cd  Xi em G, excluindo C
>

```
#Dataset Test y = m(x)
X Y
1 3
2 2


object = BNC(X,G,Theta)
object.train(T)
pval = object.predict(C=0)

Y'
3
2

score = metric.f1
RESULT = score(Y,Y')
```
## BNC  to TAN 

>
> TAN approx de BNC 
> TAN  means Tree argumented Naive Bayes Classifier 
>

**TAN (learning):** 
1. T -> Graph (LL ou MDL)  
2. PRIM ou Kruskal -> MWST   
3. Choose a root and add the node C

**TAN (predict)**

**TAN(score)**


## Code Diagram 

```
|Packet1 - Nodes |
|  +  Node       | ----> import | Pack2 - Data Structure        | 
                                |  + MWST                       |
                                |  + Graph                      |
                    
    (Pack2 )---->import | Pack3 - TAN              |                
                        |   + TAN_T                |----------->| Pack5 - App |
                        |   + Probability          |            |  # BNC      |
                                                                |             |   ---> | Pack6 - GUI            | 
                        |Pack4 - score      |                   |             |        |  # Run                 |    
                        |+ scorefunction    | ----------------->|             |        |                        |
                                                                                       |                        |
                                                | Pack7 - File management | import --->|                        |
                                                |  +read                  |                             
                                                |  +print                 |                          

```











