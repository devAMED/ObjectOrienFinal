Task 1 – List Management

*Overview:
It uses two predefined lists to build a third list, then removes elements simultaneously according to the indices in list 1.
The code also works with any other List<Integer> / List<String> you supply at runtime.
----------------
Algorithm:
Build phase
For each x in list 1 compute idx = x + 1.
If idx is inside list 2, append list2[idx] + x to list 3.
Otherwise trigger error handling and skip.
Simultaneous-remove phase
Remove from list 3 every element whose index equals a value in list 1.
Invalid indices raise the same warning and are ignored.
-------------
Error Handling (“Big Values”)
A big value is any index that falls outside its target list.
When this occurs the program prints exactly one diagnostic line:
Big value — index <n> is invalid for list<m> (size <s>)
The original animation flashes such cells in red; our console message mirrors that behaviour without terminating the run.
Screenshot
The image below shows the test page highlighting index 10 in red when list 3 contains only 10 elements.
<img width="938" alt="Screenshot 2025-07-03 at 10 06 08 AM" src="https://github.com/user-attachments/assets/a31ee652-bc1c-4396-a096-0ace55cbe7a2" />
