/**[MEMBER, BOOK]

LibraryState
==
[members : POWER MEMBER;
 books : POWER BOOK;
 borrowed : MEMBER <-> BOOK;
 available : POWER BOOK;
 borrowLimit : MEMBER -> NAT |
 
 (forall m : MEMBER @
    #( { b : BOOK | (m,b) in borrowed } ) <= borrowLimit m)
]
RegisterMember
==
[Delta LibraryState;
 m? : MEMBER |

 m? /: members

 members' = members union {m?} /\
 books' = books /\
 borrowed' = borrowed /\
 available' = available /\
 borrowLimit' = borrowLimit
]
BorrowBook
==
[Delta LibraryState;
 m? : MEMBER;
 b? : BOOK |

 m? in members /\
 b? in available /\
 (m?, b?) /: borrowed /\

 borrowed' = borrowed union {(m?, b?)} /\
 available' = available \ {b?} /\
 members' = members /\
 books' = books /\
 borrowLimit' = borrowLimit
]
ReturnBook
==
[Delta LibraryState;
 m? : MEMBER;
 b? : BOOK |

 (m?, b?) in borrowed /\

 borrowed' = borrowed \ {(m?, b?)} /\
 available' = available union {b?} /\
 members' = members /\
 books' = books /\
 borrowLimit' = borrowLimit
]
/** 
*
*/
module fjg{
}