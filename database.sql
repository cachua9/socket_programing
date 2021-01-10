DROP TABLE IF EXISTS `questions`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `question` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `a1` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `a2` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `a3` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `a4` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `at` int(11) NOT NULL
);

CREATE TABLE `users` (
  `user_ID` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` int(11) DEFAULT 0
);

ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_ID`);

ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `user_ID` int(11) NOT NULL AUTO_INCREMENT;