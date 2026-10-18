#!/usr/bin/env bash
# Compile each Java file on its own (solutions share class names like Solution).
# Its own folder is on the sourcepath for sibling classes; the stubs supply
# LeetCode-provided types such as TreeNode. Used by GitHub Actions and GitLab CI.
set -uo pipefail

failed=0
while IFS= read -r -d '' f; do
  out=$(mktemp -d)
  if ! javac -Xlint:none -sourcepath "$(dirname "$f"):.github/java-stubs" -d "$out" "$f"; then
    if [ -n "${GITHUB_ACTIONS:-}" ]; then
      echo "::error file=$f::Failed to compile"
    else
      echo "FAILED: $f"
    fi
    failed=1
  fi
done < <(find . -name '*.java' -not -path './.github/*' -print0)
exit $failed
