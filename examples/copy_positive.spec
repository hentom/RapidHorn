func main()
{
  Int[] a;
  const Int[] b;
  const Int blength;
  assume(0 <= blength);
  assume(blength < 3); // even 2 is to big for the proof to terminate

  Int i = 0;
  Int alength = 0;
  while(i < blength)
  {
    if (b[i] >= 0)
    {
      a[alength] = b[i];
      alength = alength + 1;
    }
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength)
  {
    // check property: all elements of a are not negative
    assert(0 <= a[j]);
    
    // check property: for every element from a there exists the same element in b
    Bool flag = false;
    Int l = 0;
    while(l < blength)
    {
      if (a[j] == b[l])
      {
        flag = true;
      }
      l = l + 1;
    }
    assert(flag);
    
    j = j + 1;
  }
}
