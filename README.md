# problem

Problem asked at flipkart. 

Input: a json file containing representations of not one but many objects.
The goal is to build a java object factory from the file:
Objects can be nested. But no cycles allowed
example
````
!com.Nested2,nested2
{
c:c
d:d
}
!com.Nested,nested
{
 a:a
 b:b
 nested2:!nested2
}
!com.example.Customer,customer1
{
    id:CUST1
    name:"john"
    email:"john@example.com"
    nested:!nested
}
!com.example.Order,order1
{
  id:"OD123"
  name:"order1"
  Customer:!customer1
  nested2:!nested2
}
````

Each object has a header line of the format `!fully.qualified.java.class.Name,referenceString`

To nest objects, use the reference. example: 
````
{ 
  address: !addressReferenceString
}
````

