-- LeetCode 196. Delete Duplicate Emails
--
-- Table Person(id int primary key, email varchar). Emails contain no uppercase letters.
-- Delete all duplicate emails, keeping only the row with the smallest id for each email.
-- Must be a DELETE statement, not a SELECT.
--
-- Self-join on email: every row that has a twin with a smaller id is a duplicate.
--
-- Time: O(n^2) worst case for the join (O(n log n) or better with an index on email)
-- Space: O(1) extra beyond the join

DELETE p1
FROM Person p1
JOIN Person p2
  ON p1.email = p2.email
 AND p1.id > p2.id;
