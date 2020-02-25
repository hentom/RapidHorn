func main()
{
  const Int[] a1;
  const Int[] a2;
  const Int alength;
  assume(0 <= alength);
  assume(alength < 3);

  Int[] b;
  Int blength = 0;

  Int i = 0;
  while(i < alength)
  {
    if(a1[i] == a2[i])
    {
      b[blength] = i;
      blength = blength + 1;
    }
    i = i + 1;
  }
  
  Int pos = 0;
  while(pos < blength)
  {
    assume(a1[b[pos]] == a2[b[pos]]);
    Bool flag = false;
    Int j = 0;
    while(j < blength)
    {
      if (b[j] == pos)
      {
        flag = true;
      }
      j = j + 1;
    }
    assert(flag == true);
    pos = pos + 1;
  }
}
